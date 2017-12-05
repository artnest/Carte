package by.carte.restaurants.ui.cities

import by.carte.restaurants.data.model.api.CitiesResponse
import by.carte.restaurants.ui.base.MvpView

interface CitiesMvpView: MvpView {

    fun updateCities(citiesList: List<CitiesResponse>)

    fun openRestaurantsActivity(cityId: String)
}
