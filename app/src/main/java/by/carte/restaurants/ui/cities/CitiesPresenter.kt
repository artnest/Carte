package by.carte.restaurants.ui.cities

import by.carte.restaurants.data.DataManager
import by.carte.restaurants.data.remote.model.CityDataItem
import by.carte.restaurants.ui.base.BasePresenter
import by.carte.restaurants.utils.rx.SchedulerProvider
import com.androidnetworking.error.ANError
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.subscribeBy

class CitiesPresenter<V : CitiesMvpView>(
        override val dataManager: DataManager,
        override val schedulerProvider: SchedulerProvider
) : CitiesMvpPresenter<V>, BasePresenter<V>(dataManager, schedulerProvider) {

    private var subscription: Disposable? = null

    override fun loadCities(regionId: String) {
        mvpView?.showLoading()
        subscription = dataManager
                .getCitiesApiCall(regionId)
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribeBy(
                        onNext = { citiesResponse ->
                            mvpView?.setData(citiesResponse.data)
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

    override fun openRestaurantsActivity(cityItem: CityDataItem) =
            mvpView!!.openRestaurantsActivity(cityItem)
}
