package by.carte.restaurants

import by.carte.restaurants.data.DataManager
import by.carte.restaurants.di.DaggerAppComponent
import dagger.android.support.DaggerApplication
import javax.inject.Inject

class CarteApp : DaggerApplication() {

    @Inject
    lateinit var dataManager: DataManager

    override fun applicationInjector() =
            DaggerAppComponent.builder().application(this).build()
}
