package com.dicoding.courseschedule.ui.setting

import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.preference.ListPreference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.SwitchPreference
import com.dicoding.courseschedule.R
import com.dicoding.courseschedule.notification.DailyReminder
import com.dicoding.courseschedule.util.NightMode

class SettingsFragment : PreferenceFragmentCompat() {

    private lateinit var dailyReminder: DailyReminder

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)
        //DONE 10 : Update theme based on value in ListPreference
        val themePreferences = findPreference<ListPreference>(getString(R.string.pref_key_dark))
        themePreferences?.setOnPreferenceChangeListener { _, newValue ->
            when (newValue) {
                "on" -> updateTheme(NightMode.ON.value)
                "off" -> updateTheme(NightMode.OFF.value)
                "auto" -> updateTheme(NightMode.AUTO.value)
            }
            true
        }

        //DONE 11 : Schedule and cancel notification in DailyReminder based on SwitchPreference
        dailyReminder = DailyReminder()
        val notificationPreferences = findPreference<SwitchPreference>(getString(R.string.pref_key_notify))
        notificationPreferences?.setOnPreferenceChangeListener { _, newValue ->
            when (newValue) {
                true -> context?.let { dailyReminder.setDailyReminder(it) }
                false -> context?.let { dailyReminder.cancelAlarm(it) }
            }
            true
        }
    }

    private fun updateTheme(nightMode: Int): Boolean {
        AppCompatDelegate.setDefaultNightMode(nightMode)
        requireActivity().recreate()
        return true
    }
}