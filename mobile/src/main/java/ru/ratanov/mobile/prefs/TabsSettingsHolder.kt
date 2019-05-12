package ru.ratanov.mobile.prefs

import android.preference.PreferenceManager
import ru.ratanov.mobile.App

object TabsSettingsHolder {

    private var country = "0"
    private var category = "0"
    private var year = "0"
    private var added = "0"

    fun set(key: String, value: String) {
        when (key) {
            "k" -> country = value
            "t" -> category = value
            "d" -> year = value
            "w" -> added = value
        }
    }

    fun save(tab: Int) {
        val query = "k=$country&t=$category&d=$year&w=$added"
        val context = App.instance()

        PreferenceManager.getDefaultSharedPreferences(context)
            .edit()
            .putString(tab.toString(), query)
            .apply()
    }
}