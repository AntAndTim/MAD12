package me.antandtim.mad12.di

import android.content.Context
import dagger.Module
import dagger.Provides
import me.antandtim.mad12.sharedpreferences.SharedPreferencesWrapper
import javax.inject.Named

@Module
class SharedPrefsModule {

    @Provides
    @Named("notSecure")
    fun provideSharedPrefs(context:Context) = SharedPreferencesWrapper(context, false)

    @Provides
    fun provideSecureSharedPrefs(context:Context) = SharedPreferencesWrapper(context, true)
}