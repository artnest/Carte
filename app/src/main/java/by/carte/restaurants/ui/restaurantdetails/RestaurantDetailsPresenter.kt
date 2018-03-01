package by.carte.restaurants.ui.restaurantdetails

import by.carte.restaurants.data.DataManager
import by.carte.restaurants.data.remote.model.CategoryDataItem
import by.carte.restaurants.data.remote.model.CityDataItem
import by.carte.restaurants.data.remote.model.RestaurantDataItem
import by.carte.restaurants.ui.base._BasePresenter
import by.carte.restaurants.utils.rx.SchedulerProvider
import com.androidnetworking.error.ANError
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.subscribeBy

class RestaurantDetailsPresenter<V : RestaurantDetailsMvpView>(
        override val dataManager: DataManager,
        override val schedulerProvider: SchedulerProvider
) : RestaurantDetailsMvpPresenter<V>, _BasePresenter<V>(dataManager, schedulerProvider) {

    private var subscription: Disposable? = null

    override fun loadCategories(regionId: String, cityId: String, restaurantId: String) {
        mvpView?.showLoading()
        subscription = dataManager
                .getCategoriesApiCall(regionId, cityId, restaurantId)
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribeBy(
                        onNext = { categoriesResponse ->
                            mvpView?.setData(categoriesResponse.data)
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

    override fun showDishes(cityItem: CityDataItem, restaurantItem: RestaurantDataItem,
                            categoryItem: CategoryDataItem) =
            mvpView!!.showDishes(cityItem, restaurantItem, categoryItem)
}
