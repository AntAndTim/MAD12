package me.antandtim.mad12.card.network

import me.antandtim.mad12.card.model.Card
import me.antandtim.mad12.common.network.BaseClient
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface CardApiClient {

    @GET("/card")
    fun get(@Query("completed") completed: Boolean? = null): Call<List<Card>>
}

val CARD_API_CLIENT: CardApiClient = BaseClient.BASE_CLIENT.create(CardApiClient::class.java)
