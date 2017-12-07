package by.carte.restaurants.di

import by.carte.restaurants.ui.cities.CitiesActivity
import by.carte.restaurants.ui.cities.CitiesActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = [(CitiesActivityModule::class)])
    abstract fun citiesActivity(): CitiesActivity
}
