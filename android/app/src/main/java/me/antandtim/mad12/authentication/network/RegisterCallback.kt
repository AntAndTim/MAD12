package me.antandtim.mad12.authentication.network

import android.content.Context
import android.widget.Toast
import me.antandtim.mad12.authentication.ui.AuthenticationActivity
import me.antandtim.mad12.authentication.model.User
import me.antandtim.mad12.common.activity.interaction.RequestCode
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterCallback(
        private val context: Context?,
        private val password: String
) : Callback<User> {

    override fun onFailure(call: Call<User>, t: Throwable) {
        Toast.makeText(context, t.localizedMessage, Toast.LENGTH_LONG).show()
    }

    override fun onResponse(
            call: Call<User>,
            response: Response<User>
    ) {
        (context as AuthenticationActivity).apply {
            securedWrapper.set("LOGIN", response.body()!!.login)
            securedWrapper.set("PASSWORD", password)
            setResult(RequestCode.AUTHENTICATION.code)
            finish()
        }
    }
}
