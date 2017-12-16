package by.carte.restaurants.data

import android.content.Context
import by.carte.restaurants.data.remote.ApiHelper

class AppDataManager(private val context: Context,
                     private val apiHelper: ApiHelper) : DataManager {

    override fun getRegionsApiCall() = apiHelper.getRegionsApiCall()

    override fun getCitiesApiCall(regionId: String) = apiHelper.getCitiesApiCall(regionId)

    override fun getRestaurantsApiCall(regionId: String, cityId: String) =
            apiHelper.getRestaurantsApiCall(regionId, cityId)

    override fun getCategoriesApiCall(regionId: String, cityId: String, restaurantId: String) =
            apiHelper.getCategoriesApiCall(regionId, cityId, restaurantId)
}
