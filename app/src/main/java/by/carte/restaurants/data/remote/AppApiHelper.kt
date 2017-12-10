package by.carte.restaurants.data.remote

import by.carte.restaurants.data.remote.model.CitiesResponse
import by.carte.restaurants.data.remote.model.RegionsResponse
import com.rx2androidnetworking.Rx2AndroidNetworking
import io.reactivex.Observable

object AppApiHelper : ApiHelper {

    override fun getRegionsApiCall(): Observable<RegionsResponse> =
            Rx2AndroidNetworking.get(ENDPOINT_REGIONS)
                    .build()
                    .getObjectObservable(RegionsResponse::class.java)

    override fun getCitiesApiCall(): Observable<CitiesResponse> =
            Rx2AndroidNetworking.get(ENDPOINT_CITIES)
                    .build()
                    .getObjectObservable(CitiesResponse::class.java)

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
