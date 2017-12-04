package by.carte.restaurants.data.remote

import by.carte.restaurants.data.model.api.CitiesResponse
import com.rx2androidnetworking.Rx2AndroidNetworking
import io.reactivex.Observable

class AppApiHelper: ApiHelper {

    override fun getCitiesApiCall(): Observable<CitiesResponse> {
        return Rx2AndroidNetworking.get(ENDPOINT_CITIES)
                .build()
                .getObjectObservable(CitiesResponse::class.java)
    }

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
