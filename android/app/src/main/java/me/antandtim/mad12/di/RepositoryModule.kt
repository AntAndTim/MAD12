package me.antandtim.mad12.di

import dagger.Module
import dagger.Provides
import me.antandtim.mad12.auth.data.LoginDataSource
import me.antandtim.mad12.auth.data.LoginRepository
import me.antandtim.mad12.sharedpreferences.SharedPreferencesWrapper
import javax.inject.Named
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Singleton
    @Provides
    fun providesRepository(@Named("secure") secureSharedPrefs: SharedPreferencesWrapper): LoginRepository {
        return LoginRepository(secureSharedPrefs)
    }
}