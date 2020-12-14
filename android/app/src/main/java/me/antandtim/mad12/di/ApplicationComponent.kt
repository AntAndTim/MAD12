package me.antandtim.mad12.di


import dagger.Component
import me.antandtim.mad12.MainFragment
import me.antandtim.mad12.authentication.ui.AuthenticationActivity
import me.antandtim.mad12.authentication.di.AuthenticationNetworkModule
import me.antandtim.mad12.authentication.ui.LoginFragment
import me.antandtim.mad12.authentication.ui.RegistrationFragment
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

    fun injectLogin(fragment: LoginFragment)
    fun injectRegistration(fragment: RegistrationFragment)
    fun injectMain(activity: MainFragment)
}