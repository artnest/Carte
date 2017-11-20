package by.carte.restaurants.ui.cities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import by.carte.restaurants.R

class CitiesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cities)

        supportFragmentManager.beginTransaction()
                .replace(R.id.container, CitiesFragment.newInstance())
                .commit()
    }
}
