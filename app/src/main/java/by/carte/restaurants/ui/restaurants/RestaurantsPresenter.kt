package by.carte.restaurants.ui.restaurants

import by.carte.restaurants.data.DataManager
import by.carte.restaurants.ui.base.BasePresenter
import by.carte.restaurants.utils.rx.SchedulerProvider
import com.androidnetworking.error.ANError
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.subscribeBy

class RestaurantsPresenter<V : RestaurantsMvpView>(
        override val dataManager: DataManager,
        override val schedulerProvider: SchedulerProvider
) : RestaurantsMvpPresenter<V>, BasePresenter<V>(dataManager, schedulerProvider) {

    private var subscription: Disposable? = null

    override fun loadRestaurants() {
        mvpView?.showLoading()
        subscription = dataManager
                .getCitiesApiCall()
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribeBy(
                        onNext = { citiesResponse ->
                            // TODO: it.data
                            mvpView?.setData(listOf())
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

    override fun openRestaurantDetailsActivity(restaurantId: String) =
            mvpView!!.openRestaurantDetailsActivity(restaurantId)
}
