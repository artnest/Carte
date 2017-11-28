package by.carte.restaurants.ui.cities

import android.annotation.SuppressLint
import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.databinding.ObservableBoolean
import com.example.android.architecture.blueprints.todoapp.SingleLiveEvent

class CitiesViewModel(context: Application,
                      private val citiesRepository: CitiesRepository) : AndroidViewModel(context) {

    private val isDataLoadingError = ObservableBoolean(false)
    @SuppressLint("StaticFieldLeak")
    private val context = context.applicationContext

    internal val openRestaurantsEvent = SingleLiveEvent<String>()
}
