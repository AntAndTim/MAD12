package me.antandtim.mad12.di


import android.app.Activity
import dagger.Component
import me.antandtim.mad12.MainActivity
import me.antandtim.mad12.auth.ui.login.AuthorizationActivity
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, RepositoryModule::class, SharedPrefsModule::class])
interface ApplicationComponent {

    fun injectLogin(activity: AuthorizationActivity)

    fun injectMain(activity: MainActivity)
}