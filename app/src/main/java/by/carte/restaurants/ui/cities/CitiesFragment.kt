package by.carte.restaurants.ui.cities


import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import by.carte.restaurants.CarteApp
import by.carte.restaurants.R
import by.carte.restaurants.data.remote.model.CityDataItem
import by.carte.restaurants.data.remote.model.RegionDataItem
import by.carte.restaurants.ui.base.BaseFragment
import by.carte.restaurants.ui.restaurants.RestaurantsActivity
import by.carte.restaurants.utils.rx.AppSchedulerProvider
import kotlinx.android.synthetic.main.fragment_cities.*
import kotlinx.android.synthetic.main.partial_error_view.*
import kotlinx.android.synthetic.main.partial_error_view.view.*
import kotlinx.android.synthetic.main.partial_loading_view.*

private const val ARGUMENT_REGION = "ARGUMENT_REGION"

class CitiesFragment : CitiesMvpView, CitiesAdapter.Callback,
        BaseFragment() {

    private lateinit var presenter: CitiesMvpPresenter<CitiesMvpView>

    private lateinit var regionItem: RegionDataItem

    private lateinit var citiesAdapter: CitiesAdapter
    private lateinit var layoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            regionItem = it.getParcelable(ARGUMENT_REGION)
        }

        presenter = CitiesPresenter(CarteApp.dataManager, AppSchedulerProvider)

        citiesAdapter = CitiesAdapter(mutableListOf())
        citiesAdapter.callback = this
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.fragment_cities, container, false)

        presenter.onAttach(this)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter.loadCities(regionItem.id.toString())
    }

    override fun setUp(view: View) {
        layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        recycler_cities.layoutManager = layoutManager
        recycler_cities.adapter = citiesAdapter
    }

    override fun showLoading() {
        text_choose_city.visibility = View.INVISIBLE
        recycler_cities.visibility = View.INVISIBLE
        loading_view.visibility = View.VISIBLE
        error_view.visibility = View.GONE
    }

    override fun setData(citiesList: List<CityDataItem>) {
        citiesAdapter.setCities(citiesList)
        citiesAdapter.notifyDataSetChanged()
    }

    override fun showContent() {
        text_choose_city.visibility = View.VISIBLE
        recycler_cities.visibility = View.VISIBLE
        loading_view.visibility = View.GONE
        error_view.visibility = View.GONE
    }

    override fun showError(message: String?) {
        text_choose_city.visibility = View.INVISIBLE
        recycler_cities.visibility = View.INVISIBLE
        loading_view.visibility = View.GONE
        error_view.visibility = View.VISIBLE
        error_view.text_retry.setOnClickListener {
            presenter.loadCities(regionItem.id.toString())
        }
        super.showError(message)
    }

    override fun onDestroyView() {
        presenter.onDetach()
        super.onDestroyView()
    }

    override fun onItemClicked(item: CityDataItem) {
        presenter.openRestaurantsActivity(item)
    }

    override fun openRestaurantsActivity(cityItem: CityDataItem) {
        val intent = RestaurantsActivity.getStartIntent(activity!!, cityItem)
        startActivity(intent)
        activity!!.finish()
    }

    companion object {
        fun newInstance(regionItem: RegionDataItem): CitiesFragment = CitiesFragment().apply {
            arguments = Bundle().apply {
                putParcelable(ARGUMENT_REGION, regionItem)
            }
        }
    }
}
