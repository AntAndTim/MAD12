package me.antandtim.mad12.card.network

import android.content.Context
import android.widget.Toast
import me.antandtim.mad12.card.activity.CreateCardActivity
import me.antandtim.mad12.card.model.Card
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CardCreateCallback(private val context: Context) : Callback<Card> {

    override fun onFailure(call: Call<Card>, t: Throwable) {
        Toast.makeText(context, t.localizedMessage, Toast.LENGTH_LONG).show()
        (context as CreateCardActivity).onFailure()
    }

    override fun onResponse(
        call: Call<Card>,
        response: Response<Card>
    ) {
        (context as CreateCardActivity).onSuccess()
    }
}
