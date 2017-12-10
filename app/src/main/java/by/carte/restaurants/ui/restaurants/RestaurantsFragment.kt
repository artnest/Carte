package by.carte.restaurants.ui.restaurants


import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import by.carte.restaurants.CarteApp
import by.carte.restaurants.R
import by.carte.restaurants.ui.base.BaseFragment
import by.carte.restaurants.ui.restaurantdetails.RestaurantDetailsActivity
import by.carte.restaurants.utils.rx.AppSchedulerProvider
import kotlinx.android.synthetic.main.fragment_restaurants.*
import kotlinx.android.synthetic.main.partial_error_view.*
import kotlinx.android.synthetic.main.partial_error_view.view.*
import kotlinx.android.synthetic.main.partial_loading_view.*

class RestaurantsFragment : RestaurantsMvpView, RestaurantsAdapter.Callback,
        BaseFragment() {

    lateinit var presenter: RestaurantsMvpPresenter<RestaurantsMvpView>

    private lateinit var restaurantsAdapter: RestaurantsAdapter
    private lateinit var layoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        presenter = RestaurantsPresenter(CarteApp.dataManager, AppSchedulerProvider)

        restaurantsAdapter = RestaurantsAdapter(mutableListOf())
        restaurantsAdapter.callback = this
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.fragment_restaurants, container, false)

        presenter.onAttach(this)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter.loadRestaurants()
    }

    override fun setUp(view: View) {
        layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        recycler_restaurants.layoutManager = layoutManager
        recycler_restaurants.adapter = restaurantsAdapter
    }

    override fun showLoading() {
        recycler_restaurants.visibility = View.INVISIBLE
        loading_view.visibility = View.VISIBLE
        error_view.visibility = View.GONE
    }

    override fun setData(restaurantsList: List<String>) {
        restaurantsAdapter.setRestaurants(restaurantsList)
        restaurantsAdapter.notifyDataSetChanged()
    }

    override fun showContent() {
        recycler_restaurants.visibility = View.VISIBLE
        loading_view.visibility = View.GONE
        error_view.visibility = View.GONE
    }

    override fun showError(message: String?) {
        recycler_restaurants.visibility = View.INVISIBLE
        loading_view.visibility = View.GONE
        error_view.visibility = View.VISIBLE
        error_view.text_retry.setOnClickListener { presenter.loadRestaurants() }
        super.showError(message)
    }

    override fun onDestroyView() {
        presenter.onDetach()
        super.onDestroyView()
    }

    override fun openRestaurantDetailsActivity(restaurantId: String) {
        val intent = RestaurantDetailsActivity.getStartIntent(activity!!, restaurantId)
        startActivity(intent)
        activity!!.finish()
    }

    override fun onItemClicked(item: String) {
        presenter.openRestaurantDetailsActivity(item)
    }

    companion object {
        fun newInstance(): RestaurantsFragment = RestaurantsFragment().apply {
            arguments = Bundle()
        }
    }
}
