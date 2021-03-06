package me.antandtim.mad12.card.network

import android.content.Context
import android.widget.Toast
import me.antandtim.mad12.card.adapter.CardAdapter
import me.antandtim.mad12.card.model.Card
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CardGetCallback(private val adapter: CardAdapter, private val context: Context) :
    Callback<List<Card>> {

    override fun onFailure(call: Call<List<Card>>, t: Throwable) {
        Toast.makeText(context, t.localizedMessage, Toast.LENGTH_LONG).show()
    }

    override fun onResponse(
        call: Call<List<Card>>,
        response: Response<List<Card>>
    ) {
        adapter.submitList(response.body())
    }
}
