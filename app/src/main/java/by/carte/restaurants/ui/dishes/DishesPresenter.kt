package by.carte.restaurants.ui.dishes

import by.carte.restaurants.data.DataManager
import by.carte.restaurants.ui.base.BasePresenter
import by.carte.restaurants.utils.rx.SchedulerProvider
import com.androidnetworking.error.ANError
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.subscribeBy

class DishesPresenter<V : DishesMvpView>(
        override val dataManager: DataManager,
        override val schedulerProvider: SchedulerProvider
) : DishesMvpPresenter<V>, BasePresenter<V>(dataManager, schedulerProvider) {

    private var subscription: Disposable? = null

    override fun loadDishes(regionId: String,
                            cityId: String,
                            restaurantId: String,
                            categoryId: String) {
        mvpView?.showLoading()
        subscription = dataManager
                .getDishesApiCall(regionId, cityId, restaurantId, categoryId)
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribeBy(
                        onNext = { dishesResponse ->
                            mvpView?.setData(dishesResponse.data)
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
}
