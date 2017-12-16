package by.carte.restaurants.ui.restaurantdetails

import by.carte.restaurants.data.remote.model.CategoryDataItem
import by.carte.restaurants.data.remote.model.CityDataItem
import by.carte.restaurants.ui.base.MvpPresenter

interface RestaurantDetailsMvpPresenter<in V : RestaurantDetailsMvpView> : MvpPresenter<V> {

    fun loadCategories(regionId: String, cityId: String, restaurantId: String)

    fun showDishes(cityItem: CityDataItem, categoryItem: CategoryDataItem)
}