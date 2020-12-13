package me.antandtim.mad12.authentication.network

import me.antandtim.mad12.authentication.model.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface UserApiClient {

    @POST("/user")
    fun register(@Body user: User): Call<User>

    @GET("/user/info")
    fun info(): Call<User>
}