package by.carte.restaurants.ui.restaurantdetails

import android.content.Context
import android.content.Intent
import android.os.Bundle
import by.carte.restaurants.R
import by.carte.restaurants.ui.base.BaseActivity
import kotlinx.android.synthetic.main.partial_toolbar.*

private const val EXTRA_RESTAURANT_ID = "EXTRA_RESTAURANT_ID"

class RestaurantDetailsActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restaurant_details)

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
        /*supportFragmentManager.findFragmentById(R.id.frame_container) ?:
                RestaurantDetailsFragment.newInstance().let {
                    replaceFragmentInActivity(it, R.id.frame_container)
                }*/
    }

    override fun onDestroy() {
        clearListeners()
        super.onDestroy()
    }

    private fun clearListeners() {
        toolbar.setOnMenuItemClickListener(null)
    }

    companion object {
        fun getStartIntent(context: Context, restaurantId: String) =
                Intent(context, RestaurantDetailsActivity::class.java)
                        .putExtra(EXTRA_RESTAURANT_ID, restaurantId)!!
    }
}
