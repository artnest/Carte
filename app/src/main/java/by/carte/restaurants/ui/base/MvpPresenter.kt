package by.carte.restaurants.ui.base

import com.androidnetworking.error.ANError

interface MvpPresenter<in V: MvpView> {

    fun onAttach(mvpView: V)

    fun onDetach()

    fun handleApiError(error: ANError)
}
