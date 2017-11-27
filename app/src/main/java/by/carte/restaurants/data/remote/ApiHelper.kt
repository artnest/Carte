package by.carte.restaurants.data.remote

import by.carte.restaurants.data.model.api.CitiesResponse
import io.reactivex.Observable

interface ApiHelper {

    fun getAvailableCities() : Observable<CitiesResponse>

    fun getRestaurantsForCity()

    fun getRestaurantInfo()

    fun getPhotoForRestaurant()
}
