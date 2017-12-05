package by.carte.restaurants.ui.restaurants

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import by.carte.restaurants.R

private const val EXTRA_CITY_ID = "EXTRA_CITY_ID"

class RestaurantsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
                .replace(R.id.container, RestaurantsFragment.newInstance())
                .commit()
    }

    companion object {
        fun getStartIntent(context: Context, cityId: String) =
                Intent(context, RestaurantsActivity::class.java)
                        .putExtra(EXTRA_CITY_ID, cityId)!!
        // TODO: add flag CLEAR_TOP ?
    }
}
