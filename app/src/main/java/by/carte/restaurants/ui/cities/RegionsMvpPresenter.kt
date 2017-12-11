package by.carte.restaurants.ui.cities

import by.carte.restaurants.data.remote.model.RegionDataItem
import by.carte.restaurants.ui.base.MvpPresenter

interface RegionsMvpPresenter<in V: RegionsMvpView>: MvpPresenter<V> {

    fun loadRegions()

    fun openCitiesFragment(regionItem: RegionDataItem)
}
