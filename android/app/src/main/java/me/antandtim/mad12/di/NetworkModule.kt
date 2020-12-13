package me.antandtim.mad12.di

import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializer
import dagger.Module
import dagger.Provides
import me.antandtim.mad12.BuildConfig
import me.antandtim.mad12.common.interceptor.AuthInterceptor
import me.antandtim.mad12.sharedpreferences.SharedPreferencesWrapper
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.time.Instant
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    @Named("unsecuredRetrofit")
    fun unsecuredRetrofit(@Named("unsecuredOk") okHttpClient: OkHttpClient) = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(createInstantConverter())
        .build()

    @Provides
    @Singleton
    @Named("securedRetrofit")
    fun securedRetrofit(@Named("securedOk") okHttpClient: OkHttpClient) = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(createInstantConverter())
        .build()

    @Provides
    @Singleton
    @Named("unsecuredOk")
    fun unsecuredHttpClientProvider() = OkHttpClient.Builder()
        .connectTimeout(60, TimeUnit.SECONDS)
        .readTimeout(60, TimeUnit.SECONDS)
        .writeTimeout(60, TimeUnit.SECONDS)
        .build()

    @Provides
    @Singleton
    @Named("securedOk")
    fun securedHttpClientProvider(authInterceptor: AuthInterceptor) = OkHttpClient.Builder()
        .connectTimeout(60, TimeUnit.SECONDS)
        .readTimeout(60, TimeUnit.SECONDS)
        .writeTimeout(60, TimeUnit.SECONDS)
        .addInterceptor(authInterceptor)
        .build()

    @Provides
    @Singleton
    fun provideAuthenticator(
        secureSharedPrefs: SharedPreferencesWrapper
    ) = AuthInterceptor(secureSharedPrefs)

    companion object {
        private fun createInstantConverter() = GsonConverterFactory.create(
            GsonBuilder().registerTypeAdapter(Instant::class.java, instantSerializer()).create()
        )

        @JvmStatic
        private fun instantSerializer() = JsonDeserializer { json, _, _ ->
            Instant.parse(json.asJsonPrimitive.asString)
        }
    }
}