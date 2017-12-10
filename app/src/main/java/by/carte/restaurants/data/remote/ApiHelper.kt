package by.carte.restaurants.data.remote

import by.carte.restaurants.data.remote.model.CitiesResponse
import by.carte.restaurants.data.remote.model.RegionsResponse
import io.reactivex.Observable

interface ApiHelper {

    fun getRegionsApiCall(): Observable<RegionsResponse>

    fun getCitiesApiCall(): Observable<CitiesResponse>

    fun getRestaurantsForCityApiCall(): Observable<CitiesResponse>

    fun getRestaurantInfoApiCall(): Observable<CitiesResponse>

    fun getPhotoForRestaurantApiCall(): Observable<CitiesResponse>
}
