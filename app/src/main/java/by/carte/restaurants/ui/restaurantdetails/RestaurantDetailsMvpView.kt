package by.carte.restaurants.ui.restaurantdetails

import by.carte.restaurants.data.remote.model.CategoryDataItem
import by.carte.restaurants.data.remote.model.CityDataItem
import by.carte.restaurants.ui.base.MvpView

interface RestaurantDetailsMvpView : MvpView {

    fun setData(categoriesList: List<CategoryDataItem>)

    fun showDishes(cityItem: CityDataItem, categoryItem: CategoryDataItem)
}
