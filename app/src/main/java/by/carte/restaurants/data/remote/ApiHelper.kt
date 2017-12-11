package by.carte.restaurants.data.remote

import by.carte.restaurants.data.remote.model.CitiesResponse
import by.carte.restaurants.data.remote.model.RegionsResponse
import by.carte.restaurants.data.remote.model.RestaurantsResponse
import io.reactivex.Observable

interface ApiHelper {

    fun getRegionsApiCall(): Observable<RegionsResponse>

    fun getCitiesApiCall(regionId: String): Observable<CitiesResponse>

    fun getRestaurantsApiCall(regionId: String, cityId: String): Observable<RestaurantsResponse>

    fun getRestaurantInfoApiCall(): Observable<CitiesResponse>

    fun getPhotoForRestaurantApiCall(): Observable<CitiesResponse>
}
