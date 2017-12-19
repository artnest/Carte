package by.carte.restaurants.ui.dishes

import by.carte.restaurants.data.remote.model.DishDataItem
import by.carte.restaurants.ui.base.MvpView

interface DishesMvpView : MvpView {

    fun setData(dishesList: List<DishDataItem>)
}
