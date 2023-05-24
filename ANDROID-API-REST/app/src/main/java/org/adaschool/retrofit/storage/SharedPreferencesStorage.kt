package org.adaschool.retrofit.storage

import TOKEN_KEY
import android.content.SharedPreferences

class SharedPreferencesStorage(private val sharedPreferences: SharedPreferences) :Storage {

    override fun saveToken(token: String) {
        sharedPreferences.edit()
            .putString(TOKEN_KEY, token)
            .apply()
    }

    override fun getToken(): String? {
        return sharedPreferences.getString(TOKEN_KEY, "")
    }

    override fun clear() {
        sharedPreferences.edit().clear().apply()
    }

}
