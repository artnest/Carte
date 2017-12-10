package by.carte.restaurants.data

import android.content.Context
import by.carte.restaurants.data.remote.ApiHelper
import by.carte.restaurants.data.remote.model.CitiesResponse
import by.carte.restaurants.data.remote.model.RegionsResponse
import io.reactivex.Observable

class AppDataManager(private val context: Context,
                     private val apiHelper: ApiHelper) : DataManager {

    override fun getRegionsApiCall(): Observable<RegionsResponse> =
            apiHelper.getRegionsApiCall()

    override fun getCitiesApiCall(): Observable<CitiesResponse> =
            apiHelper.getCitiesApiCall()

    override fun getRestaurantsForCityApiCall(): Observable<CitiesResponse> {
        TODO("not implemented")
    }

    override fun getRestaurantInfoApiCall(): Observable<CitiesResponse> {
        TODO("not implemented")
    }

    override fun getPhotoForRestaurantApiCall(): Observable<CitiesResponse> {
        TODO("not implemented")
    }
}
