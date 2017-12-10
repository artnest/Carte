package by.carte.restaurants.ui.splash

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import by.carte.restaurants.R
import by.carte.restaurants.ui.cities.CitiesActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restaurants)

        val intent = CitiesActivity.getStartIntent(this)
        startActivity(intent)
        finish()
    }
}
