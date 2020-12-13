package me.antandtim.mad12

import android.app.Application
import me.antandtim.mad12.di.ApplicationComponent
import me.antandtim.mad12.di.DaggerApplicationComponent
import me.antandtim.mad12.di.SharedPrefsModule


class CardApplication : Application() {

    lateinit var appComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        APPLICATION = this

        appComponent =
            DaggerApplicationComponent.builder().sharedPrefsModule(SharedPrefsModule()).build()
    }

    companion object {
        lateinit var APPLICATION: Application
    }
}