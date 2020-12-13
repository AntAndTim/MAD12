package me.antandtim.mad12.authentication.di

import dagger.Module
import dagger.Provides
import me.antandtim.mad12.authentication.network.UserApiClient
import retrofit2.Retrofit
import javax.inject.Named

@Module
class AuthenticationNetworkModule {

    @Provides
    fun userApiClient(@Named("unsecuredRetrofit") retrofit: Retrofit): UserApiClient =
        retrofit.create(UserApiClient::class.java)
}