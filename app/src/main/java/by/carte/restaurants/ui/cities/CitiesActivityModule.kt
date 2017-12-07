package by.carte.restaurants.ui.cities

import by.carte.restaurants.di.FragmentScope
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class CitiesActivityModule {

    @ContributesAndroidInjector
    abstract fun citiesFragment(): @FragmentScope CitiesFragment
}
