package by.carte.restaurants.ui.base

import android.content.Context
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import android.widget.Toast
import by.carte.restaurants.R
import by.carte.restaurants.utils.NetworkUtils

abstract class BaseFragment : MvpView, Fragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUp(view)
    }

    protected abstract fun setUp(view: View)

    override fun showLoading() {
    }

    override fun showContent() {
    }

    override fun showError(resId: Int) = showError(getString(resId))

    override fun showError(message: String?) =
            message?.let { showSnackbar(it) } ?: showSnackbar(getString(R.string.error_api_default))

    private fun showSnackbar(message: String) {
        activity?.let {
            val snackbar = Snackbar.make(it.findViewById(android.R.id.content), message, Snackbar.LENGTH_SHORT)
            val snackbarTextView = snackbar.view.findViewById<TextView>(android.support.design.R.id.snackbar_text)
            snackbarTextView.setTextColor(ContextCompat.getColor(it, android.R.color.white))
            snackbar.show()
        }
    }

    override fun showMessage(resId: Int) = showMessage(getString(resId))

    override fun showMessage(message: String?) {
        activity?.let {
            message?.let { Toast.makeText(activity!!, message, Toast.LENGTH_SHORT).show() }
                    ?: Toast.makeText(activity!!, getString(R.string.error_api_default), Toast.LENGTH_SHORT).show()
        }
    }
    // TODO: use toast() from Anko or implement by myself

    override fun isNetworkConnected() = NetworkUtils.isNetworkConnected(activity!!.applicationContext)

    override fun hideKeyboard() {
        activity?.let {
            val view = it.currentFocus
            view?.let {
                val imm = activity!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(view.windowToken, 0)
            }
        }
    }

    interface Callback {

        fun onFragmentAttached()

        fun onFragmentDetached(tag: String)
    }
}
