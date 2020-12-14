package me.antandtim.mad12.authentication.activity

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_authentication.*
import me.antandtim.mad12.CardApplication
import me.antandtim.mad12.R
import me.antandtim.mad12.authentication.model.User
import me.antandtim.mad12.authentication.network.CardCheckAuthCallback
import me.antandtim.mad12.authentication.network.RegisterCallback
import me.antandtim.mad12.authentication.network.UserApiClient
import me.antandtim.mad12.card.network.CardApiClient
import me.antandtim.mad12.sharedpreferences.SharedPreferencesWrapper
import javax.inject.Inject

class AuthenticationActivity : AppCompatActivity() {

    @Inject
    lateinit var securedWrapper: SharedPreferencesWrapper

    @Inject
    lateinit var userApiClient: UserApiClient

    @Inject
    lateinit var cardApiClient: CardApiClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authentication)
        (application as CardApplication).appComponent.injectAuthentication(this)

        loginButton.isEnabled = login.text!!.isNotBlank() && password.text!!.isNotBlank()

        login.addTextChangedListener(AuthenticationActivityHelper.getTextWatcher(this))
        password.addTextChangedListener(AuthenticationActivityHelper.getTextWatcher(this))

        loginButton.setOnClickListener {
            cardApiClient.get().enqueue(
                CardCheckAuthCallback(
                    this,
                    login.text.toString(),
                    password.text.toString()
                )
            )
        }

        registerButton.setOnClickListener {
            if(registerButton.text == getString(R.string.create_account_button)){
                loginText.hint = getString(R.string.login_text)
                loginButton.text = getString(R.string.create_account_button)
                registerButton.text = getString(R.string.back)
                textView3.visibility = View.GONE

                loginButton.setOnClickListener {
                    userApiClient
                            .register(User(login.text.toString(), password.text.toString()))
                            .enqueue(RegisterCallback(this, password.text.toString()))
                }
            } else {
                loginText.hint = getString(R.string.login_text)
                loginButton.text = getString(R.string.log_in_button)
                registerButton.text = getString(R.string.create_account_button)
                textView3.visibility = View.VISIBLE

                loginButton.setOnClickListener {
                    cardApiClient.get().enqueue(
                            CardCheckAuthCallback(
                                    this,
                                    login.text.toString(),
                                    password.text.toString()
                            )
                    )
                }
            }

        }
    }
}