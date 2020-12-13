package me.antandtim.mad12.card.di

import dagger.Module
import dagger.Provides
import me.antandtim.mad12.card.network.CardApiClient
import retrofit2.Retrofit
import javax.inject.Named

@Module
class CardNetworkModule {

    @Provides
    fun cardApiClient(@Named("securedRetrofit") retrofit: Retrofit) =
        retrofit.create(CardApiClient::class.java)
}