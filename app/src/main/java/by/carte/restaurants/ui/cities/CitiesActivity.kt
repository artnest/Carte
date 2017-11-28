package by.carte.restaurants.ui.cities

import android.arch.lifecycle.Observer
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import by.carte.restaurants.R
import by.carte.restaurants.ui.restaurants.RestaurantsActivity
import by.carte.restaurants.util.replaceFragmentInActivity
import com.example.android.architecture.blueprints.todoapp.util.obtainViewModel
import com.example.android.architecture.blueprints.todoapp.util.replaceFragmentInActivity
import kotlinx.android.synthetic.main.partial_toolbar.*

class CitiesActivity : AppCompatActivity() {

    private lateinit var viewModel: CitiesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cities)

        setupToolbar()

        setupViewFragment()

        viewModel = obtainViewModel().apply {
            openRestaurantsEvent.observe(this@CitiesActivity, Observer { cityId ->
                cityId?.let {
                    openRestaurantsForCity(cityId)
                }
            })
        }
    }

    private fun setupToolbar() {
        toolbar.inflateMenu(R.menu.activity_cities)
        toolbar.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.menu_search_city -> true // TODO: open search
                else -> false
            }
        }
        toolbar.setTitle(R.string.app_name)
    }

    private fun setupViewFragment() {
        supportFragmentManager.findFragmentById(R.id.frame_container) ?:
                CitiesFragment.newInstance().let {
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

    override fun openRestaurantsForCity(cityId: String) {
        val intent = Intent(this, RestaurantsActivity::class.java).apply {
            putExtra(RestaurantsActivity.EXTRA_CITY_ID, cityId)
        }
        // TODO: add flag CLEAR_TOP
        startActivity(intent)
        finish()
    }

    fun obtainViewModel() = obtainViewModel(CitiesViewModel::class.java)
}
