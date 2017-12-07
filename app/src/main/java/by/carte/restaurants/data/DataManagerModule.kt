package by.carte.restaurants.data

import dagger.Binds
import dagger.Module

@Module
abstract class DataManagerModule {

    @Binds
    abstract fun getAppDataManager(appDataManager: AppDataManager): DataManager
}
