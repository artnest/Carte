package by.carte.restaurants.ui.base

import android.content.Context
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

abstract class BaseFragment<T: ViewDataBinding, V: BaseViewModel<*>>: Fragment() {

    open lateinit var activity: BaseActivity<T, V>
    private lateinit var viewDataBinding: T
    private lateinit var viewModel: V
    private lateinit var rootView: View

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is BaseActivity<*, *>) {
            activity = context as BaseActivity<T, V>
            // activity.onFragmentAttached()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        // performDependencyInjection()
        super.onCreate(savedInstanceState)

        viewModel = getViewModel()
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewDataBinding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)
        rootView = viewDataBinding.root
        return rootView
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewDataBinding.setVariable(getBindingVariable(), viewModel)
        viewDataBinding.executePendingBindings()
    }
}
