package by.carte.restaurants.ui.base

import android.util.Log
import by.carte.restaurants.data.DataManager
import by.carte.restaurants.utils.rx.SchedulerProvider
import com.androidnetworking.error.ANError

open class BasePresenter<V : MvpView>(
        open val dataManager: DataManager,
        open val schedulerProvider: SchedulerProvider
) : MvpPresenter<V> {

    var mvpView: V? = null
        private set

    override fun onAttach(mvpView: V) {
        this.mvpView = mvpView
    }

    override fun onDetach() {
        mvpView = null
    }

    override fun handleApiError(error: ANError) {
        // TODO: handle api error
        Log.d("API_ERROR", "error_code")
        mvpView?.showError(error.message)
    }

    class MvpViewNotAttachedException() : RuntimeException(
            "Please call Presenter.onAttach(MvpView) before requesting data to the Presenter"
    )
}
