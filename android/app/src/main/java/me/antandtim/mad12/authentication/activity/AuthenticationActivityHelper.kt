package me.antandtim.mad12.authentication.activity

import android.text.Editable
import android.text.TextWatcher
import kotlinx.android.synthetic.main.activity_authentication.*

object AuthenticationActivityHelper {

    fun getTextWatcher(authenticationActivity: AuthenticationActivity): TextWatcher {
        return LoginTextWatcher(authenticationActivity)
    }
}

private class LoginTextWatcher(private val authenticationActivity: AuthenticationActivity) :
    TextWatcher {

    override fun afterTextChanged(s: Editable?) {
        authenticationActivity.loginButton.isEnabled =
            authenticationActivity.login.text!!.isNotBlank()
                    && authenticationActivity.password.text!!.isNotBlank()
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

    }
}
