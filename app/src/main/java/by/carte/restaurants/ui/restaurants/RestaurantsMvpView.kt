package by.carte.restaurants.ui.restaurants

import by.carte.restaurants.data.remote.model.CityDataItem
import by.carte.restaurants.data.remote.model.RestaurantDataItem
import by.carte.restaurants.ui.base.MvpView

interface RestaurantsMvpView : MvpView {

    fun setData(restaurantsList: List<RestaurantDataItem>)

    fun openRestaurantDetailsActivity(cityItem: CityDataItem, restaurantItem: RestaurantDataItem)

    // fun openMapActivity(cityItem: CityDataItem, restaurantItem: RestaurantDataItem)
}
