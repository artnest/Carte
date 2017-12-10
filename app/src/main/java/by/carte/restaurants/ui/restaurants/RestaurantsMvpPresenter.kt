package by.carte.restaurants.ui.restaurants

import by.carte.restaurants.ui.base.MvpPresenter

interface RestaurantsMvpPresenter<in V : RestaurantsMvpView> : MvpPresenter<V> {

    fun loadRestaurants()

    fun openRestaurantDetailsActivity(restaurantId: String)
}