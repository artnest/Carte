package by.carte.restaurants.ui.base

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.View

abstract class _BaseFragment : MvpView, Fragment() {

    var activity: _BaseActivity? = null
        private set

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is _BaseActivity) {
            activity = context
            activity!!.onFragmentAttached()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUp(view)
    }

    protected abstract fun setUp(view: View)

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onDetach() {
        activity = null
        super.onDetach()
    }

    override fun showLoading() {
    }

    override fun showContent() {
    }

    override fun showError(resId: Int) = showError(getString(resId))

    override fun showError(message: String?) {
        activity?.showError(message)
    }

    override fun showMessage(resId: Int) = showMessage(getString(resId))

    override fun showMessage(message: String?) {
        activity?.showMessage(message)
    }

    override fun isNetworkConnected() = activity?.isNetworkConnected() ?: false

    override fun hideKeyboard() {
        activity?.hideKeyboard()
    }

    interface Callback {

        fun onFragmentAttached()

        fun onFragmentDetached(tag: String)
    }
}
