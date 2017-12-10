package by.carte.restaurants.ui.restaurants

import by.carte.restaurants.ui.base.MvpView

interface RestaurantsMvpView : MvpView {

    fun setData(restaurantsList: List<String>)

    fun openRestaurantDetailsActivity(restaurantId: String)
}
