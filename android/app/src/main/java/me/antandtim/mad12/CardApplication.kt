package me.antandtim.mad12

import android.app.Application
import me.antandtim.mad12.di.ApplicationComponent
import me.antandtim.mad12.di.DaggerApplicationComponent


class CardApplication : Application() {

    lateinit var appComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        APPLICATION = this

        appComponent = DaggerApplicationComponent.builder().build()
    }

    companion object {
        lateinit var APPLICATION: Application
    }
}