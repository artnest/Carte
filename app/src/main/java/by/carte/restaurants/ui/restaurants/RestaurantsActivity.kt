package by.carte.restaurants.ui.restaurants

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import by.carte.restaurants.R

class RestaurantsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
                .replace(R.id.container, RestaurantsFragment.newInstance())
                .commit()
    }
}
