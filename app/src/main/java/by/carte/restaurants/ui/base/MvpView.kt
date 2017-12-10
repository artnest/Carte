package by.carte.restaurants.ui.base

import android.support.annotation.StringRes

interface MvpView {

    fun showLoading()

    fun showContent()

    fun showError(@StringRes resId: Int)

    fun showError(message: String?)

    fun showMessage(@StringRes resId: Int)

    fun showMessage(message: String?)

    fun isNetworkConnected(): Boolean

    fun hideKeyboard()
}
