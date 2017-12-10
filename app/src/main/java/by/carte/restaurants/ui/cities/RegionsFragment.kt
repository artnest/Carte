package by.carte.restaurants.ui.cities


import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import by.carte.restaurants.CarteApp
import by.carte.restaurants.R
import by.carte.restaurants.data.remote.model.RegionDataItem
import by.carte.restaurants.ui.base.BaseFragment
import by.carte.restaurants.utils.replaceFragmentInActivity
import by.carte.restaurants.utils.rx.AppSchedulerProvider
import kotlinx.android.synthetic.main.fragment_regions.*
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
        progress_loading.visibility = View.VISIBLE
    }

    override fun setData(regionsList: List<RegionDataItem>) {
        regionsAdapter.setRegions(regionsList)
        regionsAdapter.notifyDataSetChanged()
    }

    override fun showContent() {
        text_choose_region.visibility = View.VISIBLE
        recycler_regions.visibility = View.VISIBLE
        progress_loading.visibility = View.GONE
    }

    override fun onDestroyView() {
        presenter.onDetach()
        super.onDestroyView()
    }

    override fun openCitiesFragment(regionId: Int) {
        activity!!.replaceFragmentInActivity(CitiesFragment.newInstance(regionId), R.id.frame_container,
                true)
    }

    override fun onItemClicked(item: RegionDataItem) {
        presenter.openCitiesFragment(item.id)
    }

    companion object {
        fun newInstance(): RegionsFragment = RegionsFragment().apply {
            arguments = Bundle()
        }
    }
}
