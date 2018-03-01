package by.carte.restaurants.ui.base

import by.carte.restaurants.R
import by.carte.restaurants.data.DataManager
import by.carte.restaurants.utils.API_STATUS_CODE_LOCAL_ERROR
import by.carte.restaurants.utils.rx.SchedulerProvider
import com.androidnetworking.common.ANConstants
import com.androidnetworking.error.ANError

open class _BasePresenter<V : MvpView>(
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
        if (error.errorCode == API_STATUS_CODE_LOCAL_ERROR
                && error.errorDetail == ANConstants.CONNECTION_ERROR) {
            mvpView?.showError(R.string.error_no_connection)
            return
        }

        if (error.errorCode == API_STATUS_CODE_LOCAL_ERROR
                && error.errorDetail == ANConstants.REQUEST_CANCELLED_ERROR) {
            mvpView?.showError(R.string.error_api_retry)
            return
        }

        mvpView?.showError(R.string.error_api_default)
    }

    class MvpViewNotAttachedException() : RuntimeException(
            "Please call Presenter.onAttach(MvpView) before requesting data to the Presenter"
    )
}
