package com.aie.app.utils

import android.content.Context
import android.content.SharedPreferences

class SharedPreferencesUtil {
    companion object {

        val LOGIN_DTL = "LOGIN_DTL"

        fun saveStatus(ctx: Context) {
            val settings: SharedPreferences = ctx.getSharedPreferences(LOGIN_DTL, 0)
            val editor = settings.edit()
            editor.putBoolean("is_saved", true)
            editor.apply()
        }

        fun getSaveStatus(ctx: Context): Boolean {
            val settings: SharedPreferences = ctx.getSharedPreferences(LOGIN_DTL, 0)
            return settings.getBoolean("is_saved", false)
        }

    }
}