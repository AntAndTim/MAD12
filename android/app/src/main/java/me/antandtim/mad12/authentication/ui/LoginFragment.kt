package me.antandtim.mad12.authentication.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_login.view.*
import me.antandtim.mad12.CardApplication
import me.antandtim.mad12.R
import me.antandtim.mad12.authentication.network.CardCheckAuthCallback
import me.antandtim.mad12.card.network.CardApiClient
import javax.inject.Inject


class LoginFragment : Fragment() {
    @Inject
    lateinit var cardApiClient: CardApiClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val a = activity

        if(a != null)
            (a.application as CardApplication).appComponent.injectLogin(this)
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_login, container, false)
        val loginButton = view.loginButton
        val registerButton = view.registerButton
        val login = view.login
        val password = view.password

        loginButton.isEnabled

        loginButton.isEnabled = login.text!!.isNotBlank() && password.text!!.isNotBlank()

        login.addTextChangedListener(LoginFragmentHelper.getTextWatcher(this))
        password.addTextChangedListener(LoginFragmentHelper.getTextWatcher(this))

        loginButton.setOnClickListener {
            cardApiClient.get().enqueue(
                CardCheckAuthCallback(
                    this.context,
                    login.text.toString(),
                    password.text.toString()
                )
            )
        }

        registerButton.setOnClickListener {
            (activity as AuthenticationActivity).goToRegister()
        }


        return view
    }
}