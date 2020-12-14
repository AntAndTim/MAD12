package me.antandtim.mad12.authentication.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import me.antandtim.mad12.CardApplication
import me.antandtim.mad12.R
import me.antandtim.mad12.sharedpreferences.SharedPreferencesWrapper
import javax.inject.Inject

class AuthenticationActivity : AppCompatActivity() {

    @Inject
    lateinit var securedWrapper: SharedPreferencesWrapper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authentication)
        (application as CardApplication).appComponent.injectAuthentication(this)
        goToLogin()
    }

    fun goToRegister(){
        val registerFragment = RegistrationFragment()
        supportFragmentManager.beginTransaction().replace(R.id.auth_body, registerFragment).commit()
    }

    fun goToLogin(){
        val loginFragment = LoginFragment()
        supportFragmentManager.beginTransaction().replace(R.id.auth_body, loginFragment).commit()
    }
}