package by.carte.restaurants.ui.base

import android.app.ProgressDialog
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.v7.app.AppCompatActivity

class BaseActivity<T: ViewDataBinding, V: BaseViewModel<*>>: AppCompatActivity() {

    private lateinit var progressDialog: ProgressDialog

    private lateinit var viewDataBinding: T
    private lateinit var viewModel: V

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}
