package by.carte.restaurants.ui.cities

import by.carte.restaurants.ui.base.MvpPresenter

interface CitiesMvpPresenter<in V: CitiesMvpView>: MvpPresenter<V> {

    fun openRestaurantsActivity(cityId: String)

    fun onViewPrepared()
}
