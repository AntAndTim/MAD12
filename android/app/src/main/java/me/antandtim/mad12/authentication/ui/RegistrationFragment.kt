package me.antandtim.mad12.authentication.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_registartion.view.*
import me.antandtim.mad12.CardApplication
import me.antandtim.mad12.R
import me.antandtim.mad12.authentication.model.User
import me.antandtim.mad12.authentication.network.RegisterCallback
import me.antandtim.mad12.authentication.network.UserApiClient
import javax.inject.Inject


class RegistrationFragment : Fragment() {
    @Inject
    lateinit var userApiClient: UserApiClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val a = activity

        if(a != null)
            (a.application as CardApplication).appComponent.injectRegistration(this)
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_registartion, container, false)
        val registerButton = view.registerButton
        val backButton = view.backButton
        val login = view.login
        val password = view.password
        val name = view.name
        val lastName = view.last_name


        registerButton.isEnabled = login.text!!.isNotBlank()
                && password.text!!.isNotBlank()
                && name.text!!.isNotBlank()
                && lastName.text!!.isNotBlank()

        login.addTextChangedListener(RegistrationFragmentHelper.getTextWatcher(this))
        password.addTextChangedListener(RegistrationFragmentHelper.getTextWatcher(this))
        name.addTextChangedListener(RegistrationFragmentHelper.getTextWatcher(this))
        lastName.addTextChangedListener(RegistrationFragmentHelper.getTextWatcher(this))

        registerButton.setOnClickListener {
                    userApiClient
                            .register(User(login.text.toString(), password.text.toString(), name.text.toString(), lastName.text.toString()))
                            .enqueue(RegisterCallback(this.context, password.text.toString()))
                }

        backButton.setOnClickListener {
            (activity as AuthenticationActivity).goToLogin()
        }


        return view
    }

}