package by.carte.restaurants.ui.dishes

import by.carte.restaurants.ui.base.MvpPresenter

interface DishesMvpPresenter<in V : DishesMvpView> : MvpPresenter<V> {

    fun loadDishes(regionId: String, cityId: String, restaurantId: String, categoryId: String)
}