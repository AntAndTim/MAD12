package me.antandtim.mad12.di

import android.content.Context
import dagger.Module
import dagger.Provides
import me.antandtim.mad12.CardApplication

@Module
class ApplicationModule {

    @Provides
    fun provideApplication() = CardApplication.APPLICATION

    @Provides
    fun provideContext(): Context = CardApplication.APPLICATION

}