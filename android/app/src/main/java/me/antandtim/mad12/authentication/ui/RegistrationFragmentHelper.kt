package me.antandtim.mad12.authentication.ui

import android.text.Editable
import android.text.TextWatcher
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_login.login
import kotlinx.android.synthetic.main.fragment_login.password
import kotlinx.android.synthetic.main.fragment_login.registerButton
import kotlinx.android.synthetic.main.fragment_registartion.*

object RegistrationFragmentHelper {

    fun getTextWatcher(registrationFragment: RegistrationFragment): TextWatcher {
        return RegistrationTextWatcher(registrationFragment)
    }
}

private class RegistrationTextWatcher(private val registrationFragment: RegistrationFragment) :
    TextWatcher {

    override fun afterTextChanged(s: Editable?) {
        registrationFragment.registerButton.isEnabled =
            registrationFragment.login.text!!.isNotBlank()
                    && registrationFragment.password.text!!.isNotBlank()
                    && registrationFragment.name.text!!.isNotBlank()
                    && registrationFragment.last_name.text!!.isNotBlank()
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

    }
}
