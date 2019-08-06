package com.kirkbushman.sampleapp.data

import android.content.Context
import androidx.core.content.edit

class Preferences(context: Context) {

    companion object {

        private const val APP_SHARED_PREFS = "sample_app_sample_preferences"

        private const val FLAG_ISLOGGED = "flag_is_logged_in"
        private const val VAL_BASEURL = "value_base_url"
        private const val VAL_USERNAME = "value_username"
        private const val VAL_PASSWORD = "value_password"
    }

    private val prefs by lazy { context.getSharedPreferences(APP_SHARED_PREFS, Context.MODE_PRIVATE) }

    fun getIsLoggedIn(): Boolean = prefs.getBoolean(FLAG_ISLOGGED, false)
    fun setIsLoggedIn(isLoggedIn: Boolean) {
        prefs.edit {

            putBoolean(FLAG_ISLOGGED, isLoggedIn)
        }
    }

    fun getBaseUrl(): String = prefs.getString(VAL_BASEURL, "") ?: ""
    fun setBaseUrl(baseUrl: String) {
        prefs.edit {

            putString(VAL_BASEURL, baseUrl)
        }
    }

    fun getUsername(): String = prefs.getString(VAL_USERNAME, "") ?: ""
    fun setUsername(username: String) {
        prefs.edit {

            putString(VAL_USERNAME, username)
        }
    }

    fun getPassword(): String = prefs.getString(VAL_PASSWORD, "") ?: ""
    fun setPassword(password: String) {
        prefs.edit {

            putString(VAL_PASSWORD, password)
        }
    }
}
