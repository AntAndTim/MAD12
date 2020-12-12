package me.antandtim.mad12.common.network

import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializer
import me.antandtim.mad12.BuildConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.time.Instant
import java.util.concurrent.TimeUnit


object BaseClient {

    val BASE_CLIENT: Retrofit = Retrofit.Builder().apply {
        baseUrl(BuildConfig.BASE_URL)
        client(OkHttpClient.Builder().apply {
            connectTimeout(60, TimeUnit.SECONDS)
            readTimeout(60, TimeUnit.SECONDS)
            writeTimeout(60, TimeUnit.SECONDS)
        }.build())
        addConverterFactory(
            GsonConverterFactory.create(
                GsonBuilder().registerTypeAdapter(Instant::class.java, instantSerializer()).create()
            )
        )
    }.build()

    @JvmStatic
    private fun instantSerializer() = JsonDeserializer { json, _, _ ->
        Instant.parse(json.asJsonPrimitive.asString)
    }
}
