package by.carte.restaurants

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider

// TODO: use object instead of class
class ViewModelFactory<V : ViewModel>(private val viewModel: V) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(viewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return viewModel as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}
