package by.carte.restaurants.ui.restaurants

import by.carte.restaurants.data.remote.model.CityDataItem
import by.carte.restaurants.data.remote.model.RestaurantDataItem
import by.carte.restaurants.ui.base.MvpPresenter

interface RestaurantsMvpPresenter<in V : RestaurantsMvpView> : MvpPresenter<V> {

    fun loadRestaurants(regionId: String, cityId: String)

    fun openRestaurantDetailsActivity(cityItem: CityDataItem, restaurantItem: RestaurantDataItem)

    // fun openMapActivity(cityItem: CityDataItem, restaurantItem: RestaurantDataItem)
}