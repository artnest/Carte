package by.carte.restaurants.utils.rx

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

object AppSchedulerProvider : SchedulerProvider {

    override fun ui() = AndroidSchedulers.mainThread()!!

    override fun computation() = Schedulers.computation()

    override fun io() = Schedulers.io()
}
