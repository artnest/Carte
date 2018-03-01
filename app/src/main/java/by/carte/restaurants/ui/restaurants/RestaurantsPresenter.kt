package by.carte.restaurants.ui.restaurants

import by.carte.restaurants.data.DataManager
import by.carte.restaurants.data.remote.model.CityDataItem
import by.carte.restaurants.data.remote.model.RestaurantDataItem
import by.carte.restaurants.ui.base._BasePresenter
import by.carte.restaurants.utils.rx.SchedulerProvider
import com.androidnetworking.error.ANError
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.subscribeBy

class RestaurantsPresenter<V : RestaurantsMvpView>(
        override val dataManager: DataManager,
        override val schedulerProvider: SchedulerProvider
) : RestaurantsMvpPresenter<V>, _BasePresenter<V>(dataManager, schedulerProvider) {

    private var subscription: Disposable? = null

    override fun loadRestaurants(regionId: String, cityId: String) {
        mvpView?.showLoading()
        subscription = dataManager
                .getRestaurantsApiCall(regionId, cityId)
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribeBy(
                        onNext = { restaurantsResponse ->
                            mvpView?.setData(restaurantsResponse.data)
                            mvpView?.showContent()
                        },
                        onError = { error ->
                            if (error is ANError) handleApiError(error)
                        }
                )
    }

    override fun onDetach() {
        super.onDetach()

        subscription?.let {
            if (it.isDisposed) it.dispose()
        }
    }

    override fun openRestaurantDetailsActivity(cityItem: CityDataItem, restaurantItem: RestaurantDataItem) =
            mvpView!!.openRestaurantDetailsActivity(cityItem, restaurantItem)
}
