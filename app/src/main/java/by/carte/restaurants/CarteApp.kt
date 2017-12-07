package by.carte.restaurants

import android.app.Application
import by.carte.restaurants.data.AppDataManager
import by.carte.restaurants.data.DataManager
import by.carte.restaurants.data.remote.AppApiHelper
import com.androidnetworking.AndroidNetworking

class CarteApp : Application() {

    companion object {
        lateinit var dataManager: DataManager
    }

    override fun onCreate() {
        super.onCreate()

        AndroidNetworking.initialize(applicationContext)

        dataManager = AppDataManager(applicationContext, AppApiHelper)
    }
}
