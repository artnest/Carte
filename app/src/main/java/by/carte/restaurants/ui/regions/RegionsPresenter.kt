package by.carte.restaurants.ui.regions

import by.carte.restaurants.data.DataManager
import by.carte.restaurants.data.remote.model.RegionDataItem
import by.carte.restaurants.ui.base.BasePresenter
import by.carte.restaurants.utils.rx.SchedulerProvider
import com.androidnetworking.error.ANError
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.subscribeBy

class RegionsPresenter<V : RegionsMvpView>(
        private val dataManager: DataManager,
        private val schedulerProvider: SchedulerProvider
) : RegionsMvpPresenter<V>, BasePresenter<V>() {

    private var subscription: Disposable? = null

    override fun loadRegions() {
        mvpView?.showLoading()
        subscription = dataManager
                .getRegionsApiCall()
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribeBy(
                        onNext = { regionsResponse ->
                            mvpView?.setData(regionsResponse.data)
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
            if (!it.isDisposed) it.dispose()
        }
    }

    override fun openCitiesFragment(regionItem: RegionDataItem) {
        mvpView!!.openCitiesFragment(regionItem)
    }
}
