package me.antandtim.mad12.auth.data

import me.antandtim.mad12.auth.data.model.LoggedInUser
import me.antandtim.mad12.sharedpreferences.SharedPreferencesWrapper

/**
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of login status and user credentials information.
 */

class LoginRepository(secureSharedPrefs: SharedPreferencesWrapper) {

    val dataSource = LoginDataSource()

    // in-memory cache of the loggedInUser object
    var user: LoggedInUser? = null
        private set

    val isLoggedIn: Boolean
        get() = user != null

    private val sharedPrefs = secureSharedPrefs

    init {
        user = getUserFromStore()
    }

    fun logout() {
        user = null
        dataSource.logout()
        storeUser(null)
    }

    fun login(username: String, password: String): Result<LoggedInUser> {
        // handle login
        val result = dataSource.login(username, password)

        if (result is Result.Success) {
            setLoggedInUser(result.data)
        }

        return result
    }

    private fun setLoggedInUser(loggedInUser: LoggedInUser) {
        this.user = loggedInUser
        storeUser(loggedInUser)

        // If user credentials will be cached in local storage, it is recommended it be encrypted
        // @see https://developer.android.com/training/articles/keystore
    }

    private fun storeUser(user: LoggedInUser?){
        if (user===null){
            sharedPrefs.set("token", "")
            sharedPrefs.set("displayName", "")
        }else{
            sharedPrefs.set("token", user.token)
            sharedPrefs.set("displayName",  user.token)
        }
    }
    private fun getUserFromStore(): LoggedInUser? {
        val token = sharedPrefs.getString("token")?:""
        val displayName = sharedPrefs.getString("displayName")?:""

        if(token != "" && displayName != "")
            return LoggedInUser(token,displayName)
        else
            return null
    }
}