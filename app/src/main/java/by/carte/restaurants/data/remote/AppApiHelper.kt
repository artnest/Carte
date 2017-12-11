package by.carte.restaurants.data.remote

import by.carte.restaurants.data.remote.model.CitiesResponse
import by.carte.restaurants.data.remote.model.RegionsResponse
import by.carte.restaurants.data.remote.model.RestaurantsResponse
import com.rx2androidnetworking.Rx2AndroidNetworking
import io.reactivex.Observable

object AppApiHelper : ApiHelper {

    override fun getRegionsApiCall(): Observable<RegionsResponse> =
            Rx2AndroidNetworking.get(ENDPOINT_REGIONS)
                    .build()
                    .getObjectObservable(RegionsResponse::class.java)

    override fun getCitiesApiCall(regionId: String): Observable<CitiesResponse> =
            Rx2AndroidNetworking.get(ENDPOINT_CITIES)
                    //.addPathParameter("region_id", regionId)
                    .build()
                    .getObjectObservable(CitiesResponse::class.java)

    override fun getRestaurantsApiCall(regionId: String, cityId: String): Observable<RestaurantsResponse> =
            Rx2AndroidNetworking.get(ENDPOINT_RESTAURANTS)
                    //.addPathParameter("region_id", regionId)
                    //.addPathParameter("city_id", cityId)
                    .build()
                    .getObjectObservable(RestaurantsResponse::class.java)

    override fun getRestaurantInfoApiCall(): Observable<CitiesResponse> {
        TODO("not implemented")
    }

    override fun getPhotoForRestaurantApiCall(): Observable<CitiesResponse> {
        TODO("not implemented")
    }
}
