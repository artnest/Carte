package by.carte.restaurants.ui.base

import android.app.ProgressDialog
import android.content.Context
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import android.widget.Toast
import by.carte.restaurants.R
import by.carte.restaurants.utils.NetworkUtils

abstract class BaseActivity : MvpView, BaseFragment.Callback,
        AppCompatActivity() {

    private lateinit var progressDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(newBase)
    }

    // TODO: request permissions (try to use RxPermissions)

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

    private fun showSnackbar(message: String) {
        val snackbar = Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_SHORT)
        val snackbarTextView = snackbar.view.findViewById<TextView>(android.support.design.R.id.snackbar_text)
        snackbarTextView.setTextColor(ContextCompat.getColor(this, android.R.color.white))
        snackbar.show()
    }

    override fun onError(resId: Int) = onError(getString(resId))

    override fun onError(message: String?) =
            message?.let { showSnackbar(it) } ?: showSnackbar(getString(R.string.error_basic))

    override fun showMessage(resId: Int) = showMessage(getString(resId))

    override fun showMessage(message: String?) =
            message?.let { Toast.makeText(this, message, Toast.LENGTH_SHORT).show() } ?:
                    Toast.makeText(this, getString(R.string.error_basic), Toast.LENGTH_SHORT).show()
    // TODO: use toast() from Anko or implement by myself

    override fun isNetworkConnected() = NetworkUtils.isNetworkConnected(applicationContext)

    override fun onFragmentAttached() {
    }

    override fun onFragmentDetached(tag: String) {
    }

    override fun hideKeyboard() {
        val view = currentFocus
        view?.let {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    protected abstract fun setUp()
}