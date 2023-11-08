package com.example.myapplication.localstorage.sharedpref

import android.app.Application
import android.content.Context.MODE_PRIVATE
import javax.inject.Inject
import javax.inject.Singleton

const val SHARED_PREF_DEFAULT = "myapp_shared_pref_default"
const val SP_USERNAME = "myapp_username"
const val SP_PASSWORD = "myapp_password"

@Singleton
class SharedPrefManager @Inject constructor(val myApplication: Application) {

    private val sharedPreferences by lazy {
        myApplication.getSharedPreferences(SHARED_PREF_DEFAULT, MODE_PRIVATE)
    }

    fun getString(key: String, default: String): String {
        return try {
            sharedPreferences.getString(key, default) ?: default
        } catch (e: Exception) {
            default
        }
    }

    fun putString(key: String, value: String) {
        sharedPreferences.edit().putString(key, value).apply()
    }

    fun checkIfPresent(key: String): Boolean = sharedPreferences.contains(key)

}