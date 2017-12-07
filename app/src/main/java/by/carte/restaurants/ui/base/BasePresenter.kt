package by.carte.restaurants.ui.base

import android.util.Log
import by.carte.restaurants.data.DataManager
import by.carte.restaurants.utils.rx.SchedulerProvider
import com.androidnetworking.error.ANError
import io.reactivex.disposables.CompositeDisposable

open class BasePresenter<V : MvpView>(
        open val dataManager: DataManager,
        open val schedulerProvider: SchedulerProvider,
        open val compositeDisposable: CompositeDisposable
) : MvpPresenter<V> {

    var mvpView: V? = null
        private set

    override fun onAttach(mvpView: V) {
        this.mvpView = mvpView
    }

    override fun onDetach() {
        compositeDisposable.dispose()
        mvpView = null
    }

    fun isViewAttached() = mvpView != null

    fun checkViewAttached() {
        if (!isViewAttached()) throw MvpViewNotAttachedException()
    }

    override fun handleApiError(error: ANError) {
        // TODO: handle api error
        Log.d("API_ERROR", "error_code")
    }

    class MvpViewNotAttachedException() : RuntimeException(
            "Please call Presenter.onAttach(MvpView) before requesting data to the Presenter"
    )
}
