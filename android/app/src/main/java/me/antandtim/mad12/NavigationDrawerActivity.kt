package me.antandtim.mad12

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_navigation.*
import kotlinx.android.synthetic.main.fragment_profile.view.*
import java.io.FileOutputStream


class NavigationDrawerActivity: AppCompatActivity() {

    private val filenameInternal = "profileFile"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation)

        setSupportActionBar(toolbar)

        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        val profileData = intent.getStringExtra("profileData")?.toString()?.split("\n")

        if (!profileData.isNullOrEmpty()) {
            createUpdateFile(filenameInternal, profileData[0]+"\n"+profileData[1], false)
        }

        if (savedInstanceState == null) {
            val fragment = MainFragment()
            supportFragmentManager.beginTransaction().replace(R.id.container, fragment)
                    .commit()
        }

    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { menuItem ->
        when (menuItem.itemId) {
            R.id.navigation_main -> {
                val fragment = MainFragment()
                supportFragmentManager.beginTransaction().replace(R.id.container, fragment, fragment.javaClass.getSimpleName())
                        .commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_profile -> {
                val fragment = ProfileFragment()
                supportFragmentManager.beginTransaction().replace(R.id.container, fragment, fragment.javaClass.getSimpleName())
                        .commit()
                return@OnNavigationItemSelectedListener true
            }

        }
        false
    }


    private fun createUpdateFile(fileName: String, content: String, update: Boolean) {
        val outputStream: FileOutputStream
        try {
            if (update) {
                outputStream = openFileOutput(fileName, Context.MODE_APPEND)!!
            } else {
                outputStream = openFileOutput(fileName, Context.MODE_PRIVATE)!!
            }
            outputStream.write(content.toByteArray())
            outputStream.flush()
            outputStream.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}