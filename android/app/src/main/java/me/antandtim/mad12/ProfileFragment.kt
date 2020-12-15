package me.antandtim.mad12

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_profile.view.*
import me.antandtim.mad12.authentication.ui.AuthenticationActivity
import me.antandtim.mad12.common.activity.interaction.RequestCode
import me.antandtim.mad12.sharedpreferences.SharedPreferencesWrapper
import java.io.BufferedReader
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.InputStreamReader
import javax.inject.Inject

class ProfileFragment : Fragment() {

    private val filenameInternal = "profileFile"

    @Inject
    lateinit var preferencesWrapper: SharedPreferencesWrapper

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        (activity?.application as CardApplication).appComponent.injectProfile(this)


        val data = readFileInternalStorage().split("\n")
        if (data.isNullOrEmpty()) {
            view.profileName.text = getString(R.string.no_data)
            view.profileLastName.text = getString(R.string.no_data)
        } else {
            view.profileName.text = data[0]
            view.profileLastName.text = data[1]
        }

        view.logout_btn.setOnClickListener {
            preferencesWrapper.set("LOGIN", "")
            preferencesWrapper.set("PASSWORD", "")
            startActivityForResult(
                    Intent(this.context, AuthenticationActivity::class.java),
                    RequestCode.AUTHENTICATION.code
            )
        }



        return view;
    }


    private fun readFileInternalStorage(): String {
        try {
            val fileInputStream: FileInputStream = context?.openFileInput(filenameInternal)!!
            val reader = BufferedReader(InputStreamReader(fileInputStream))
            val sb = StringBuffer()
            var line = reader.readLine()
            while (line != null) {
                sb.append(line + "\n")
                line = reader.readLine()
            }
            return sb.toString()
        } catch (e: Exception) {
            e.printStackTrace()
            return getString(R.string.no_data)
        }
    }
}