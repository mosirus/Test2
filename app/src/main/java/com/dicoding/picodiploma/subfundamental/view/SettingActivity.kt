package com.dicoding.picodiploma.subfundamental.view

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import com.dicoding.picodiploma.subfundamental.R
import com.dicoding.picodiploma.subfundamental.databinding.ActivitySettingBinding
import com.dicoding.picodiploma.subfundamental.reminder.ReminderReceiver

class SettingActivity : AppCompatActivity() {

    private lateinit var binding : ActivitySettingBinding
    private lateinit var reminderReceiver: ReminderReceiver


    companion object {
        private const val PREFS_NAME = "reminder_preference"
        private const val IS_REMINDER_ACTIVATED = "is_reminder"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = getString(R.string.settings)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        reminderReceiver = ReminderReceiver()

        val reminderSharedPreferences: SharedPreferences =
                this.getSharedPreferences(
                        PREFS_NAME,
                        Context.MODE_PRIVATE
                )

        binding.switchRemainder.isChecked =
                reminderSharedPreferences.getBoolean(IS_REMINDER_ACTIVATED, false)


        binding.switchRemainder.setOnClickListener {
            if(binding.switchRemainder.isChecked) {
                val editor = reminderSharedPreferences.edit()
                editor.putBoolean(IS_REMINDER_ACTIVATED, true)
                editor.apply()
                binding.switchRemainder.isChecked = true

                val messageRemind = getString(R.string.message_reminder)
                reminderReceiver.setRepeatingAlarm(this, messageRemind)
            } else {
                val editor = reminderSharedPreferences.edit()
                editor.putBoolean(IS_REMINDER_ACTIVATED, false)
                editor.apply()
                binding.switchRemainder.isChecked = false

                reminderReceiver.cancelReminder(this)
            }
        }

        binding.btnLanguange.setOnClickListener {
            Intent(Settings.ACTION_LOCALE_SETTINGS).also {
                    startActivity(it)
                }
        }

    }
}