package com.outlinetrip.littlelemon.utils

import android.content.SharedPreferences

object SharedPreferencesCommons {

    fun saveStringToStringInSharedPreference(key:String, value:String, userSharedPreferences: SharedPreferences){
        userSharedPreferences.edit()
            .putString(key, value)
            .apply()
    }
    fun saveStringBooleanKPInSharedPreference(key:String, value:Boolean, userSharedPreferences: SharedPreferences){
        userSharedPreferences.edit()
            .putBoolean(key, value)
            .apply()
    }
}