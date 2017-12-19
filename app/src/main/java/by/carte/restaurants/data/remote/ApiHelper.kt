package by.carte.restaurants.data.remote

import by.carte.restaurants.data.remote.model.*
import io.reactivex.Observable

interface ApiHelper {

    fun getRegionsApiCall(): Observable<RegionsResponse>

    fun getCitiesApiCall(regionId: String): Observable<CitiesResponse>

    fun getRestaurantsApiCall(regionId: String, cityId: String): Observable<RestaurantsResponse>

    fun getCategoriesApiCall(regionId: String, cityId: String, restaurantId: String): Observable<CategoriesResponse>

    fun getDishesApiCall(regionId: String, cityId: String, restaurantId: String, categoryId: String): Observable<DishesResponse>
}
