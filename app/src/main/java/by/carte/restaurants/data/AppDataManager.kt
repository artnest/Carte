package by.carte.restaurants.data

import android.content.Context
import by.carte.restaurants.data.local.DbHelper
import by.carte.restaurants.data.model.api.CitiesResponse
import by.carte.restaurants.data.remote.ApiHelper
import io.reactivex.Observable
import javax.inject.Singleton

@Singleton
class AppDataManager(private val context: Context,
                     private val dbHelper: DbHelper,
                     private val apiHelper: ApiHelper) : DataManager {

    override fun insertCity() {
        dbHelper.insertCity()
    }

    override fun getAllCities() {
        dbHelper.getAllCities()
    }

    override fun getAllRestaurants() {
        dbHelper.getAllRestaurants()
    }

    override fun getAvailableCities(): Observable<CitiesResponse> {
        return apiHelper.getAvailableCities()
    }

    override fun getRestaurantsForCity() {
        apiHelper.getRestaurantsForCity()
    }

    override fun getRestaurantInfo() {
        apiHelper.getRestaurantInfo()
    }

    override fun getPhotoForRestaurant() {
        apiHelper.getPhotoForRestaurant()
    }
}
