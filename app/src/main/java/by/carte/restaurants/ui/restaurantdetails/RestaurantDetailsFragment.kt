package by.carte.restaurants.ui.restaurantdetails


import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import by.carte.restaurants.CarteApp
import by.carte.restaurants.R
import by.carte.restaurants.data.remote.model.CategoryDataItem
import by.carte.restaurants.data.remote.model.CityDataItem
import by.carte.restaurants.data.remote.model.RestaurantDataItem
import by.carte.restaurants.ui.base.BaseFragment
import by.carte.restaurants.ui.dishes.DishesFragment
import by.carte.restaurants.utils.loadImage
import by.carte.restaurants.utils.replaceFragmentInActivity
import by.carte.restaurants.utils.rx.AppSchedulerProvider
import kotlinx.android.synthetic.main.fragment_restaurant_details.*
import kotlinx.android.synthetic.main.partial_error_view.*
import kotlinx.android.synthetic.main.partial_error_view.view.*
import kotlinx.android.synthetic.main.partial_loading_view.*

private const val ARGUMENT_CITY = "ARGUMENT_CITY"
private const val ARGUMENT_RESTAURANT = "ARGUMENT_RESTAURANT"

class RestaurantDetailsFragment : RestaurantDetailsMvpView, CategoriesAdapter.Callback,
        BaseFragment() {

    private lateinit var presenter: RestaurantDetailsMvpPresenter<RestaurantDetailsMvpView>

    private lateinit var cityItem: CityDataItem
    private lateinit var restaurantItem: RestaurantDataItem

    private lateinit var categoriesAdapter: CategoriesAdapter
    private lateinit var layoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            cityItem = it.getParcelable(ARGUMENT_CITY)
            restaurantItem = it.getParcelable(ARGUMENT_RESTAURANT)
        }

        presenter = RestaurantDetailsPresenter(CarteApp.dataManager, AppSchedulerProvider)

        categoriesAdapter = CategoriesAdapter(mutableListOf())
        categoriesAdapter.callback = this
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.fragment_restaurant_details, container, false)

        presenter.onAttach(this)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (restaurantItem.images.isNotEmpty()) {
            image_interior.loadImage(restaurantItem.images[0])
            // TODO: implement image slider with view pager for images list
        }
        if (!restaurantItem.orderable) text_delivery.visibility = View.GONE
        if (!restaurantItem.bookable) text_booking.visibility = View.GONE
        /*if (restaurantItem.worktime.isNotEmpty()) {
            text_open_time.text = restaurantItem.worktime
        } else {
            text_open_time.visibility = View.GONE
        }*/
        if (restaurantItem.average.isNotEmpty()) {
            text_average_bill.text = restaurantItem.average
        } else {
            text_average_bill.visibility = View.GONE
        }

        presenter.loadCategories(cityItem.regionId.toString(), cityItem.id.toString(),
                restaurantItem.id.toString())
    }

    override fun setUp(view: View) {
        layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        recycler_categories.layoutManager = layoutManager
        recycler_categories.adapter = categoriesAdapter
    }

    override fun showLoading() {
        image_interior.visibility = View.INVISIBLE
        text_delivery.visibility = View.INVISIBLE
        text_booking.visibility = View.INVISIBLE
        // text_open_time.visibility = View.INVISIBLE
        text_average_bill.visibility = View.INVISIBLE
        recycler_categories.visibility = View.INVISIBLE
        loading_view.visibility = View.VISIBLE
        error_view.visibility = View.GONE
    }

    override fun setData(categoriesList: List<CategoryDataItem>) {
        categoriesAdapter.setCategories(categoriesList)
        categoriesAdapter.notifyDataSetChanged()
    }

    override fun showContent() {
        image_interior.visibility = View.VISIBLE
        text_delivery.visibility = View.VISIBLE
        text_booking.visibility = View.VISIBLE
        // text_open_time.visibility = View.VISIBLE
        text_average_bill.visibility = View.VISIBLE
        recycler_categories.visibility = View.VISIBLE
        loading_view.visibility = View.GONE
        error_view.visibility = View.GONE
    }

    override fun showError(message: String?) {
        image_interior.visibility = View.INVISIBLE
        text_delivery.visibility = View.INVISIBLE
        text_booking.visibility = View.INVISIBLE
        // text_open_time.visibility = View.INVISIBLE
        text_average_bill.visibility = View.INVISIBLE
        recycler_categories.visibility = View.INVISIBLE
        loading_view.visibility = View.GONE
        error_view.visibility = View.VISIBLE
        error_view.text_retry.setOnClickListener {
            presenter.loadCategories(cityItem.regionId.toString(), cityItem.id.toString(),
                    restaurantItem.id.toString())
        }
        super.showError(message)
    }

    override fun onDestroyView() {
        presenter.onDetach()
        super.onDestroyView()
    }

    override fun onItemClicked(categoryItem: CategoryDataItem) {
        presenter.showDishes(cityItem, restaurantItem, categoryItem)
    }

    override fun showDishes(cityItem: CityDataItem, restaurantItem: RestaurantDataItem,
                            categoryItem: CategoryDataItem) {
        (activity as AppCompatActivity).replaceFragmentInActivity(DishesFragment.newInstance(cityItem, restaurantItem, categoryItem),
                R.id.frame_container,
                true)
    }

    companion object {
        fun newInstance(cityItem: CityDataItem,
                        restaurantItem: RestaurantDataItem) =
                RestaurantDetailsFragment().apply {
                    arguments = Bundle().apply {
                        putParcelable(ARGUMENT_CITY, cityItem)
                        putParcelable(ARGUMENT_RESTAURANT, restaurantItem)
                    }
                }
    }
}
