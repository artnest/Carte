package by.carte.restaurants.ui.cities

import by.carte.restaurants.data.DataManager
import by.carte.restaurants.di.ActivityScope
import by.carte.restaurants.ui.base.BasePresenter
import by.carte.restaurants.utils.rx.SchedulerProvider
import com.androidnetworking.error.ANError
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject

@ActivityScope
class CitiesPresenter<V : CitiesMvpView>
@Inject constructor(override val dataManager: DataManager,
                    override val schedulerProvider: SchedulerProvider,
                    override val compositeDisposable: CompositeDisposable) :
        CitiesMvpPresenter<V>,
        BasePresenter<V>(dataManager, schedulerProvider, compositeDisposable) {

    override fun openRestaurantsActivity(cityId: String) =
            mvpView!!.openRestaurantsActivity(cityId)

    override fun onViewPrepared() {
        mvpView!!.showLoading()
        compositeDisposable.add(dataManager
                .getCitiesApiCall()
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribeBy(
                        onNext = { citiesResponse ->
                            // TODO: it.data
                            citiesResponse?.let { mvpView!!.updateCities(listOf(citiesResponse)) }
                        },
                        onError = { error ->
                            if (!isViewAttached()) return@subscribeBy

                            mvpView!!.hideLoading()

                            if (error is ANError) handleApiError(error)
                        }
                ))
    }
}
