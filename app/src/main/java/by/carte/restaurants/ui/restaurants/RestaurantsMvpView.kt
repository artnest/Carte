package by.carte.restaurants.ui.restaurants

import by.carte.restaurants.data.remote.model.RestaurantDataItem
import by.carte.restaurants.ui.base.MvpView

interface RestaurantsMvpView : MvpView {

    fun setData(restaurantsList: List<RestaurantDataItem>)

    fun openRestaurantDetailsActivity(restaurantItem: RestaurantDataItem)
}
