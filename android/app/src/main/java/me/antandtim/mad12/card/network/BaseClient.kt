package me.antandtim.mad12.card.network

import me.antandtim.mad12.BuildConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object BaseClient {

    private val BASE_CLIENT = Retrofit.Builder().apply {
        baseUrl(BuildConfig.BASE_URL)
        client(OkHttpClient.Builder().apply {
            connectTimeout(60, TimeUnit.SECONDS)
            readTimeout(60, TimeUnit.SECONDS)
            writeTimeout(60, TimeUnit.SECONDS)
        }.build())
        addConverterFactory(GsonConverterFactory.create())
    }.build()

    val CARD_API_CLIENT: CardApiClient = BASE_CLIENT.create(CardApiClient::class.java)
}
