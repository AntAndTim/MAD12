package me.antandtim.mad12.card.network

import me.antandtim.mad12.card.model.Card
import retrofit2.Call
import retrofit2.http.GET

interface CardApiClient {

    @GET("/card")
    fun get(): Call<List<Card>>
}
