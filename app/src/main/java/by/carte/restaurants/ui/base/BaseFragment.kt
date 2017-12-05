package by.carte.restaurants.ui.base

import android.app.ProgressDialog
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.View

abstract class BaseFragment : MvpView, Fragment() {

    var activity: BaseActivity? = null
        private set

    private lateinit var progressDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUp(view)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is BaseActivity) {
            activity = context
            activity!!.onFragmentAttached()
        }
    }

    override fun showLoading() {
        hideLoading()
        // TODO: show loading dialog
    }

    override fun hideLoading() {
        // TODO: hide loading dialog
        /*if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.cancel();
        }*/
    }

    override fun onError(resId: Int) {
        activity?.onError(resId)
    }

    override fun onError(message: String?) {
        activity?.onError(message)
    }

    override fun showMessage(resId: Int) {
        activity?.showMessage(resId)
    }

    override fun showMessage(message: String?) {
        activity?.showMessage(message)
    }

    override fun isNetworkConnected() = activity?.isNetworkConnected() ?: false

    override fun onDetach() {
        activity = null
        super.onDetach()
    }

    override fun hideKeyboard() {
        activity?.hideKeyboard()
    }

    protected abstract fun setUp(view: View)

    override fun onDestroy() {
        super.onDestroy()
    }

    interface Callback {

        fun onFragmentAttached()

        fun onFragmentDetached(tag: String)
    }
}