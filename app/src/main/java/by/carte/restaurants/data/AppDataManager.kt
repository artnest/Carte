package by.carte.restaurants.data

import android.content.Context
import by.carte.restaurants.data.remote.ApiHelper
import by.carte.restaurants.data.remote.model.CitiesResponse
import by.carte.restaurants.data.remote.model.RegionsResponse
import by.carte.restaurants.data.remote.model.RestaurantsResponse
import io.reactivex.Observable

class AppDataManager(private val context: Context,
                     private val apiHelper: ApiHelper) : DataManager {

    override fun getRegionsApiCall(): Observable<RegionsResponse> =
            apiHelper.getRegionsApiCall()

    override fun getCitiesApiCall(regionId: String): Observable<CitiesResponse> =
            apiHelper.getCitiesApiCall(regionId)

    override fun getRestaurantsApiCall(regionId: String, cityId: String): Observable<RestaurantsResponse> =
            apiHelper.getRestaurantsApiCall(regionId, cityId)

    override fun getRestaurantInfoApiCall(): Observable<CitiesResponse> {
        TODO("not implemented")
    }

    override fun getPhotoForRestaurantApiCall(): Observable<CitiesResponse> {
        TODO("not implemented")
    }
}
