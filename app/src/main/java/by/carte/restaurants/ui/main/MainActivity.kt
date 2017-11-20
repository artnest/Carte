package by.carte.restaurants.ui.main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import by.carte.restaurants.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commit()
    }
}
