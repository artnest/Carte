package by.carte.restaurants.data.remote

import by.carte.restaurants.data.model.api.CitiesResponse
import io.reactivex.Observable

interface ApiHelper {

    fun getCitiesApiCall(): Observable<CitiesResponse>

    fun getRestaurantsForCityApiCall(): Observable<CitiesResponse>

    fun getRestaurantInfoApiCall(): Observable<CitiesResponse>

    fun getPhotoForRestaurantApiCall(): Observable<CitiesResponse>
}
