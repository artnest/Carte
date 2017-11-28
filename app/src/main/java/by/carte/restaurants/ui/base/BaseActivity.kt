package by.carte.restaurants.ui.base

import android.app.ProgressDialog
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v7.app.AppCompatActivity

abstract class BaseActivity<T : ViewDataBinding, V : BaseViewModel<*>> : AppCompatActivity() {

    private lateinit var progressDialog: ProgressDialog

    private lateinit var viewDataBinding: T
    private lateinit var viewModel: V

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // performDependencyInjection()
        performDataBinding()
    }

    private fun performDataBinding() {
        viewDataBinding = DataBindingUtil.setContentView(this, getLayoutId())
        viewModel = getViewModel()
        viewDataBinding.setVariable(getBindingVariable(), viewModel)
        viewDataBinding.executePendingBindings()
    }

    // fun isNetworkConnected(): Boolean

    fun showLoading() {
        hideLoading()
        // progressDialog = CommonUtils.showLoadingDialog(this)
    }

    fun hideLoading() {
        if (progressDialog.isShowing) {
            progressDialog.cancel()
        }
    }

    fun getViewDataBinding() = viewDataBinding

    abstract fun getViewModel(): V

    abstract fun getBindingVariable(): Int

    @LayoutRes
    abstract fun getLayoutId(): Int
}
