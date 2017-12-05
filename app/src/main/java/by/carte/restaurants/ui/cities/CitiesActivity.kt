package by.carte.restaurants.ui.cities

import android.os.Bundle
import by.carte.restaurants.R
import by.carte.restaurants.ui.base.BaseActivity
import by.carte.restaurants.utils.replaceFragmentInActivity
import kotlinx.android.synthetic.main.partial_toolbar.*

class CitiesActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cities)

        setUp()
    }

    override fun setUp() {
        setupToolbar()
        setupViewFragment()
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
}
