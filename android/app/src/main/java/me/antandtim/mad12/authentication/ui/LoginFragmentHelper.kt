package me.antandtim.mad12.authentication.ui

import android.text.Editable
import android.text.TextWatcher
import kotlinx.android.synthetic.main.fragment_login.*

object LoginFragmentHelper {

    fun getTextWatcher(loginFragment: LoginFragment): TextWatcher {
        return LoginTextWatcher(loginFragment)
    }
}

private class LoginTextWatcher(private val loginFragment: LoginFragment) :
    TextWatcher {

    override fun afterTextChanged(s: Editable?) {
        loginFragment.loginButton.isEnabled =
            loginFragment.login.text!!.isNotBlank()
                    && loginFragment.password.text!!.isNotBlank()
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

    }
}
