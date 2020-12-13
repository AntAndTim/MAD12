package me.antandtim.mad12.common.interceptor

import android.util.Base64
import me.antandtim.mad12.sharedpreferences.SharedPreferencesWrapper
import okhttp3.Interceptor
import okhttp3.Response

/**
 * Interceptor to add auth params to requests
 */
class AuthInterceptor(private val secureSharedPrefs: SharedPreferencesWrapper) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()
        val login = secureSharedPrefs.getString("LOGIN")
        val password = secureSharedPrefs.getString("PASSWORD")
        val token = Base64
            .encodeToString("$login:$password".toByteArray(), Base64.DEFAULT)
            .removeSuffix("\n")
        requestBuilder.addHeader(
            "Authorization",
            "Basic $token"
        )

        return chain.proceed(requestBuilder.build())
    }
}