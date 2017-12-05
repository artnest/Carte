package by.carte.restaurants

import android.app.Application
import by.carte.restaurants.data.DataManager

class CarteApp : Application() {

    lateinit var dataManager: DataManager

    override fun onCreate() {
        super.onCreate()
    }
}
