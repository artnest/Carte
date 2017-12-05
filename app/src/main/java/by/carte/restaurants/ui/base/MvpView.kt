package by.carte.restaurants.ui.base

import android.support.annotation.StringRes

interface MvpView {

    fun showLoading()

    fun hideLoading()

    fun onError(@StringRes resId: Int)

    fun onError(message: String?)

    fun showMessage(@StringRes resId: Int)

    fun showMessage(message: String?)

    fun isNetworkConnected(): Boolean

    fun hideKeyboard()
}
