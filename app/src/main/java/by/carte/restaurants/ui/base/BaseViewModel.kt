package by.carte.restaurants.ui.base

import android.arch.lifecycle.ViewModel
import android.databinding.ObservableBoolean
import by.carte.restaurants.data.DataManager
import by.carte.restaurants.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel<N : Navigator>(val dataManager: DataManager,
                                            val schedulerProvider: SchedulerProvider) : ViewModel() {

    lateinit var navigator: N
    val isLoading = ObservableBoolean(false)

    val compositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }
}
