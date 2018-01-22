package by.carte.restaurants.ui.regions


import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import by.carte.restaurants.CarteApp
import by.carte.restaurants.R
import by.carte.restaurants.data.remote.model.RegionDataItem
import by.carte.restaurants.ui.base.BaseFragment
import by.carte.restaurants.ui.cities.CitiesFragment
import by.carte.restaurants.utils.replaceFragmentInActivity
import by.carte.restaurants.utils.rx.AppSchedulerProvider
import kotlinx.android.synthetic.main.fragment_regions.*
import kotlinx.android.synthetic.main.partial_error_view.*
import kotlinx.android.synthetic.main.partial_error_view.view.*
import kotlinx.android.synthetic.main.partial_loading_view.*

class RegionsFragment : RegionsMvpView, RegionsAdapter.Callback,
        BaseFragment() {

    lateinit var presenter: RegionsMvpPresenter<RegionsMvpView>

    private lateinit var regionsAdapter: RegionsAdapter
    private lateinit var layoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        presenter = RegionsPresenter(CarteApp.dataManager, AppSchedulerProvider)

        regionsAdapter = RegionsAdapter(mutableListOf())
        regionsAdapter.callback = this
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.fragment_regions, container, false)

        presenter.onAttach(this)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter.loadRegions()
    }

    override fun setUp(view: View) {
        layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        recycler_regions.layoutManager = layoutManager
        recycler_regions.adapter = regionsAdapter
    }

    override fun showLoading() {
        text_choose_region.visibility = View.INVISIBLE
        recycler_regions.visibility = View.INVISIBLE
        loading_view.visibility = View.VISIBLE
        error_view.visibility = View.GONE
    }

    override fun setData(regionsList: List<RegionDataItem>) {
        regionsAdapter.setRegions(regionsList)
        regionsAdapter.notifyDataSetChanged()
    }

    override fun showContent() {
        text_choose_region.visibility = View.VISIBLE
        recycler_regions.visibility = View.VISIBLE
        loading_view.visibility = View.GONE
        error_view.visibility = View.GONE
    }

    override fun showError(message: String?) {
        text_choose_region.visibility = View.INVISIBLE
        recycler_regions.visibility = View.INVISIBLE
        loading_view.visibility = View.GONE
        error_view.visibility = View.VISIBLE
        error_view.text_retry.setOnClickListener { presenter.loadRegions() }
        super.showError(message)
    }

    override fun onDestroyView() {
        presenter.onDetach()
        super.onDestroyView()
    }

    override fun onItemClicked(item: RegionDataItem) {
        presenter.openCitiesFragment(item)
    }

    override fun openCitiesFragment(regionItem: RegionDataItem) {
        activity!!.replaceFragmentInActivity(CitiesFragment.newInstance(regionItem),
                R.id.frame_container,
                true)
    }

    companion object {
        fun newInstance(): RegionsFragment = RegionsFragment().apply {
            arguments = Bundle()
        }
    }
}
