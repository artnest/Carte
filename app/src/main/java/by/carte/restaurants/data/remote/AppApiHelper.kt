package by.carte.restaurants.data.remote

import by.carte.restaurants.data.remote.model.*
import com.rx2androidnetworking.Rx2AndroidNetworking

object AppApiHelper : ApiHelper {

    override fun getRegionsApiCall() =
            Rx2AndroidNetworking.get(ENDPOINT_REGIONS)
                    .build()
                    .getObjectObservable(RegionsResponse::class.java)!!

    override fun getCitiesApiCall(regionId: String) =
            Rx2AndroidNetworking.get(ENDPOINT_CITIES)
                    //.addPathParameter("region_id", regionId)
                    .build()
                    .getObjectObservable(CitiesResponse::class.java)!!

    override fun getRestaurantsApiCall(regionId: String, cityId: String) =
            Rx2AndroidNetworking.get(ENDPOINT_RESTAURANTS)
                    //.addPathParameter("region_id", regionId)
                    //.addPathParameter("city_id", cityId)
                    .build()
                    .getObjectObservable(RestaurantsResponse::class.java)!!

    override fun getCategoriesApiCall(regionId: String, cityId: String, restaurantId: String) =
            Rx2AndroidNetworking.get(ENDPOINT_CATEGORIES)
                    //.addPathParameter("region_id", regionId)
                    //.addPathParameter("city_id", cityId)
                    //.addPathParameter("restaurant_id", restaurantId)
                    .build()
                    .getObjectObservable(CategoriesResponse::class.java)!!

    override fun getDishesApiCall(regionId: String,
                                  cityId: String,
                                  restaurantId: String,
                                  categoryId: String) =
            Rx2AndroidNetworking.get(ENDPOINT_DISHES)
                    //.addPathParameter("region_id", regionId)
                    //.addPathParameter("city_id", cityId)
                    //.addPathParameter("restaurant_id", restaurantId)
                    //.addPathParameter("category_id", categoryId)
                    .build()
                    .getObjectObservable(DishesResponse::class.java)!!
}
