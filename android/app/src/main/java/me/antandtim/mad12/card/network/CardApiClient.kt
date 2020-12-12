package me.antandtim.mad12.card.network

import me.antandtim.mad12.card.model.Card
import me.antandtim.mad12.common.network.BaseClient
import retrofit2.Call
import retrofit2.http.*

interface CardApiClient {

    @GET("/card")
    fun get(@Query("completed") completed: Boolean? = false): Call<List<Card>>

    @PUT("/card/complete/{id}")
    fun complete(@Path("id") id: Long): Call<Card>

    @POST("/card")
    fun create(@Body card: Card): Call<Card>
}

val CARD_API_CLIENT: CardApiClient = BaseClient.BASE_CLIENT.create(CardApiClient::class.java)
