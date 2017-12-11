package by.carte.restaurants.ui.cities

import by.carte.restaurants.data.remote.model.CityDataItem
import by.carte.restaurants.ui.base.MvpPresenter

interface CitiesMvpPresenter<in V: CitiesMvpView>: MvpPresenter<V> {

    fun loadCities(regionId: String)

    fun openRestaurantsActivity(cityItem: CityDataItem)
}
