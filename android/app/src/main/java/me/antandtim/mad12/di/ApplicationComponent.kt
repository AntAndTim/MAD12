package me.antandtim.mad12.di


import dagger.Component
import me.antandtim.mad12.MainActivity
import me.antandtim.mad12.authentication.activity.AuthenticationActivity
import me.antandtim.mad12.authentication.di.AuthenticationNetworkModule
import me.antandtim.mad12.card.activity.CardActivity
import me.antandtim.mad12.card.activity.CreateCardActivity
import me.antandtim.mad12.card.di.CardNetworkModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ApplicationModule::class,
        RepositoryModule::class,
        SharedPrefsModule::class,
        NetworkModule::class,
        CardNetworkModule::class,
        AuthenticationNetworkModule::class
    ]
)
interface ApplicationComponent {

    fun injectCreateCardActivity(activity: CreateCardActivity)

    fun injectCardActivity(activity: CardActivity)

    fun injectAuthentication(activity: AuthenticationActivity)

    fun injectMain(activity: MainActivity)
}