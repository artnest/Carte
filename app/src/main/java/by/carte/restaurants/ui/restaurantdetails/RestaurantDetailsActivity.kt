package by.carte.restaurants.ui.restaurantdetails

import android.content.Context
import android.content.Intent
import android.os.Bundle
import by.carte.restaurants.R
import by.carte.restaurants.data.remote.model.CityDataItem
import by.carte.restaurants.data.remote.model.RestaurantDataItem
import by.carte.restaurants.ui.base._BaseActivity
import by.carte.restaurants.utils.replaceFragmentInActivity
import kotlinx.android.synthetic.main.partial_toolbar.*

private const val EXTRA_CITY = "EXTRA_CITY"
private const val EXTRA_RESTAURANT = "EXTRA_RESTAURANT"

class RestaurantDetailsActivity : _BaseActivity() {

    private lateinit var cityItem: CityDataItem
    private lateinit var restaurantItem: RestaurantDataItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restaurants)

        intent?.let {
            cityItem = it.getParcelableExtra(EXTRA_CITY)
            restaurantItem = it.getParcelableExtra(EXTRA_RESTAURANT)
        }

        setUp()
    }

    override fun setUp() {
        setupToolbar()
        setupViewFragment()
    }

    private fun setupToolbar() {
        /*toolbar.inflateMenu(R.menu.activity_restaurants)
        toolbar.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.menu_search_restaurant -> true // TODO: open search
                R.id.menu_open_settings -> true // TODO: open settings
                else -> false
            }
        }*/
        toolbar.setTitle(R.string.app_name_short)
    }

    private fun setupViewFragment() {
        supportFragmentManager.findFragmentById(R.id.frame_container)
                ?: RestaurantDetailsFragment.newInstance(cityItem, restaurantItem).let {
                    replaceFragmentInActivity(it, R.id.frame_container)
                }
    }

    override fun onDestroy() {
        clearListeners()
        super.onDestroy()
    }

    private fun clearListeners() {
        toolbar.setOnMenuItemClickListener(null)
    }

    companion object {
        fun getStartIntent(context: Context, cityItem: CityDataItem, restaurantItem: RestaurantDataItem) =
                Intent(context, RestaurantDetailsActivity::class.java)
                        .putExtra(EXTRA_CITY, cityItem)!!
                        .putExtra(EXTRA_RESTAURANT, restaurantItem)!!
    }
}
