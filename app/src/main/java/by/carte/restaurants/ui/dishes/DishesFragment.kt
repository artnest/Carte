package by.carte.restaurants.ui.dishes

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import by.carte.restaurants.CarteApp
import by.carte.restaurants.R
import by.carte.restaurants.data.remote.model.CategoryDataItem
import by.carte.restaurants.data.remote.model.CityDataItem
import by.carte.restaurants.data.remote.model.DishDataItem
import by.carte.restaurants.data.remote.model.RestaurantDataItem
import by.carte.restaurants.ui.base._BaseFragment
import by.carte.restaurants.utils.rx.AppSchedulerProvider
import kotlinx.android.synthetic.main.fragment_dishes.*
import kotlinx.android.synthetic.main.partial_error_view.*
import kotlinx.android.synthetic.main.partial_error_view.view.*
import kotlinx.android.synthetic.main.partial_loading_view.*

private const val ARGUMENT_CITY = "ARGUMENT_CITY"
private const val ARGUMENT_RESTAURANT = "ARGUMENT_RESTAURANT"
private const val ARGUMENT_CATEGORY = "ARGUMENT_CATEGORY"

class DishesFragment : DishesMvpView, _BaseFragment() {

    lateinit var presenter: DishesMvpPresenter<DishesMvpView>

    private lateinit var cityItem: CityDataItem
    private lateinit var restaurantItem: RestaurantDataItem
    private lateinit var categoryItem: CategoryDataItem

    private lateinit var dishesAdapter: DishesAdapter
    private lateinit var layoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            cityItem = it.getParcelable(ARGUMENT_CITY)
            restaurantItem = it.getParcelable(ARGUMENT_RESTAURANT)
            categoryItem = it.getParcelable(ARGUMENT_CATEGORY)
        }

        presenter = DishesPresenter(CarteApp.dataManager, AppSchedulerProvider)

        dishesAdapter = DishesAdapter(mutableListOf())
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.fragment_dishes, container, false)

        presenter.onAttach(this)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter.loadDishes(cityItem.regionId.toString(),
                cityItem.id.toString(),
                restaurantItem.id.toString(),
                categoryItem.id.toString())
    }

    override fun setUp(view: View) {
        layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        recycler_dishes.layoutManager = layoutManager
        recycler_dishes.adapter = dishesAdapter
    }

    override fun showLoading() {
        recycler_dishes.visibility = View.INVISIBLE
        loading_view.visibility = View.VISIBLE
        error_view.visibility = View.GONE
    }

    override fun setData(dishesList: List<DishDataItem>) {
        dishesAdapter.setDishes(dishesList)
        dishesAdapter.notifyDataSetChanged()
    }

    override fun showContent() {
        recycler_dishes.visibility = View.VISIBLE
        loading_view.visibility = View.GONE
        error_view.visibility = View.GONE
    }

    override fun showError(message: String?) {
        recycler_dishes.visibility = View.INVISIBLE
        loading_view.visibility = View.GONE
        error_view.visibility = View.VISIBLE
        error_view.text_retry.setOnClickListener {
            presenter.loadDishes(cityItem.regionId.toString(),
                    cityItem.id.toString(),
                    restaurantItem.id.toString(),
                    categoryItem.id.toString())
        }
        super.showError(message)
    }

    override fun onDestroyView() {
        presenter.onDetach()
        super.onDestroyView()
    }

    companion object {
        fun newInstance(cityItem: CityDataItem,
                        restaurantItem: RestaurantDataItem,
                        categoryItem: CategoryDataItem) =
                DishesFragment().apply {
                    arguments = Bundle().apply {
                        putParcelable(ARGUMENT_CITY, cityItem)
                        putParcelable(ARGUMENT_RESTAURANT, restaurantItem)
                        putParcelable(ARGUMENT_CATEGORY, categoryItem)
                    }
                }
    }
}
