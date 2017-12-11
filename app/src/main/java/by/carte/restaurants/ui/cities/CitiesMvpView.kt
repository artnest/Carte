package by.carte.restaurants.ui.cities

import by.carte.restaurants.data.remote.model.CityDataItem
import by.carte.restaurants.ui.base.MvpView

interface CitiesMvpView: MvpView {

    fun setData(citiesList: List<CityDataItem>)

    fun openRestaurantsActivity(cityItem: CityDataItem)
}
