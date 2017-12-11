package by.carte.restaurants.ui.cities

import by.carte.restaurants.data.remote.model.RegionDataItem
import by.carte.restaurants.ui.base.MvpView

interface RegionsMvpView : MvpView {

    fun setData(regionsList: List<RegionDataItem>)

    fun openCitiesFragment(regionItem: RegionDataItem)
}
