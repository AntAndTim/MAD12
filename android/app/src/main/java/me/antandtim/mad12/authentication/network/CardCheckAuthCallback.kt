package me.antandtim.mad12.authentication.network

import android.content.Context
import android.widget.Toast
import me.antandtim.mad12.authentication.activity.AuthenticationActivity
import me.antandtim.mad12.card.model.Card
import me.antandtim.mad12.common.activity.interaction.RequestCode
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CardCheckAuthCallback(
        private val context: Context,
        private val login: String,
        private val password: String
) : Callback<List<Card>> {

    override fun onFailure(call: Call<List<Card>>, t: Throwable) {
        Toast.makeText(context, t.localizedMessage, Toast.LENGTH_LONG).show()
    }

    override fun onResponse(
            call: Call<List<Card>>,
            response: Response<List<Card>>
    ) {
        (context as AuthenticationActivity).apply {
            securedWrapper.set("LOGIN", login)
            securedWrapper.set("PASSWORD", password)
            setResult(RequestCode.AUTHENTICATION.code)
            finish()
        }
    }
}
