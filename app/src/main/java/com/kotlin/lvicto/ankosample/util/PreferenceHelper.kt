package com.kotlin.lvicto.ankosample.util

import android.content.Context
import org.jetbrains.anko.defaultSharedPreferences

class PreferenceHelper {

    companion object {

        fun hasItems(context: Context, table: String): Boolean
                = context.defaultSharedPreferences.getBoolean(table, false)

        fun setHasItems(context: Context, table: String, hasItems: Boolean) {
            context.defaultSharedPreferences.edit().putBoolean(table, hasItems).apply()
        }
    }
}