package by.carte.restaurants.ui.cities


import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import by.carte.restaurants.R
import by.carte.restaurants.data.model.api.CitiesResponse
import by.carte.restaurants.ui.base.BaseFragment
import by.carte.restaurants.ui.restaurants.RestaurantsActivity
import kotlinx.android.synthetic.main.fragment_cities.*

class CitiesFragment : CitiesMvpView, BaseFragment() {

    lateinit var presenter: CitiesMvpPresenter<CitiesMvpView>

    private lateinit var citiesAdapter: CitiesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // presenter = CitiesPresenter(AppDataManager(), AppSchedulerProvider(), CompositeDisposable())
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.fragment_cities, container, false)!!

        presenter.onAttach(this)
        return view
    }

    override fun setUp(view: View) {
        val layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        recycler_cities.layoutManager = layoutManager
        citiesAdapter = CitiesAdapter(mutableListOf())
        recycler_cities.adapter = citiesAdapter

        presenter.onViewPrepared()
    }

    override fun updateCities(citiesList: List<CitiesResponse>) =
            citiesAdapter.addItems(citiesList)

    override fun onDestroyView() {
        presenter.onDetach()
        super.onDestroyView()
    }

    override fun openRestaurantsActivity(cityId: String) {
        val intent = RestaurantsActivity.getStartIntent(activity!!, cityId)
        startActivity(intent)
        activity!!.finish()
    }

    companion object {
        fun newInstance(): CitiesFragment = CitiesFragment().apply {
            arguments = Bundle()
        }
    }
}
