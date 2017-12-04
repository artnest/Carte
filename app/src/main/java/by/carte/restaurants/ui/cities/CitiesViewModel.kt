package by.carte.restaurants.ui.cities

import android.annotation.SuppressLint
import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.databinding.ObservableArrayList
import android.databinding.ObservableBoolean
import android.databinding.ObservableList
import com.example.android.architecture.blueprints.todoapp.SingleLiveEvent
import org.openjdk.tools.javadoc.internal.doclets.toolkit.util.DocPath.empty

class CitiesViewModel(context: Application,
                      private val citiesRepository: CarteRepository) : AndroidViewModel(context) {

    private val isDataLoadingError = ObservableBoolean(false)
    @SuppressLint("StaticFieldLeak")
    private val context = context.applicationContext

    internal val openRestaurantsEvent = SingleLiveEvent<String>()

    val items: ObservableList<City> = ObservableArrayList()

    fun start() {
        laodCities(false)
    }

    fun loadCities(forceUpdate: Boolean) {
        loadCities(forceUpdate, true)
    }

    private fun loadCities(forceUpdate: Boolean, showLoadingUI: Boolean) {
        if (showLoadingUI) {
            dataLoading.set(true)
        }
        if (forceUpdate) {
            citiesRepository.refreshCities()
        }

        citiesRepository.getCities(object {
            // TODO: regions: List<Region> ?
            override fun onCitiesLoaded(cities: List<City>) {
                if (showLoadingUI) {
                    dataLoading.set(false)
                }
                isDataLoadingError.set(false)

                with(items) {
                    clear()
                    addAll(cities)
                    empty.set(isEmpty())
                }
            }

            override fun onDataNotAvailable() {
                isDataLoadingError.set(true)
            }
        })
    }

    private fun showSnackbarMessage(message: Int) {
        snackbarMessage.value = message
    }
}
