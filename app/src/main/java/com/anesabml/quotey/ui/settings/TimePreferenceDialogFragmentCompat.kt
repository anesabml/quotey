package com.anesabml.quotey.ui.settings

import android.os.Build
import android.os.Bundle
import android.text.format.DateFormat
import android.view.View
import android.widget.TimePicker
import androidx.preference.PreferenceDialogFragmentCompat
import com.anesabml.quotey.R


class TimePreferenceDialogFragmentCompat : PreferenceDialogFragmentCompat() {

    private lateinit var timePicker: TimePicker

    companion object {
        fun newInstance(key: String) =
            TimePreferenceDialogFragmentCompat().apply {
                arguments = Bundle().apply {
                    putString(ARG_KEY, key)
                }
            }
    }

    @Suppress("DEPRECATION")
    override fun onBindDialogView(view: View) {
        super.onBindDialogView(view)

        timePicker = view.findViewById(R.id.time_picker) as TimePicker

        // Get the time from the related Preference
        var minutesAfterMidnight: Int? = null
        val preference = preference
        if (preference is TimePreference) {
            minutesAfterMidnight = preference.getTime()
        }

        // Set the time to the TimePicker
        if (minutesAfterMidnight != null) {
            val hours = minutesAfterMidnight / 60
            val minutes = minutesAfterMidnight % 60
            val is24hour = DateFormat.is24HourFormat(context)

            timePicker.setIs24HourView(is24hour)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                timePicker.hour = hours
                timePicker.minute = minutes
            } else {
                timePicker.currentHour = hours
                timePicker.currentMinute = minutes
            }

        }
    }

    @Suppress("DEPRECATION")
    override fun onDialogClosed(positiveResult: Boolean) {
        if (positiveResult) {
            // generate value to save

            val hours = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                timePicker.hour
            } else {
                timePicker.currentHour
            }
            val minutes = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                timePicker.minute
            } else {
                timePicker.currentMinute
            }

            val minutesAfterMidnight = hours * 60 + minutes

            // Get the related Preference and save the value
            val preference = preference
            if (preference is TimePreference) {
                // This allows the client to ignore the user value.
                if (preference.callChangeListener(
                        minutesAfterMidnight
                    )
                ) {
                    // Save the value
                    preference.setTime(minutesAfterMidnight)
                }
            }
        }
    }
}