package by.carte.restaurants.ui.cities


import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import by.carte.restaurants.CarteApp
import by.carte.restaurants.R
import by.carte.restaurants.data.remote.model.CityDataItem
import by.carte.restaurants.ui.base.BaseFragment
import by.carte.restaurants.ui.restaurants.RestaurantsActivity
import by.carte.restaurants.utils.rx.AppSchedulerProvider
import kotlinx.android.synthetic.main.fragment_cities.*
import kotlinx.android.synthetic.main.partial_loading_view.*

private const val ARGUMENT_REGION_ID = "ARGUMENT_REGION_ID"

class CitiesFragment : CitiesMvpView, CitiesAdapter.Callback,
        BaseFragment() {

    lateinit var presenter: CitiesMvpPresenter<CitiesMvpView>

    private var regionId: Int = 0

    private lateinit var citiesAdapter: CitiesAdapter
    private lateinit var layoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            regionId = it.getInt(ARGUMENT_REGION_ID)
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

        presenter.loadCities()
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
        progress_loading.visibility = View.VISIBLE
    }

    override fun setData(citiesList: List<CityDataItem>) {
        citiesAdapter.setCities(citiesList)
        citiesAdapter.notifyDataSetChanged()
    }

    override fun showContent() {
        text_choose_city.visibility = View.VISIBLE
        recycler_cities.visibility = View.VISIBLE
        progress_loading.visibility = View.GONE
    }

    override fun onDestroyView() {
        presenter.onDetach()
        super.onDestroyView()
    }

    override fun openRestaurantsActivity(cityId: Int) {
        val intent = RestaurantsActivity.getStartIntent(activity!!, cityId)
        startActivity(intent)
        activity!!.finish()
    }

    override fun onItemClicked(item: CityDataItem) {
        presenter.openRestaurantsActivity(item.id)
    }

    companion object {
        fun newInstance(regionId: Int): CitiesFragment = CitiesFragment().apply {
            arguments = Bundle().apply {
                putInt(ARGUMENT_REGION_ID, regionId)
            }
        }
    }
}
