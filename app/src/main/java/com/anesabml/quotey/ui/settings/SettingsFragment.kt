package com.anesabml.quotey.ui.settings

import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import com.anesabml.quotey.R


class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)
    }

    override fun onDisplayPreferenceDialog(preference: Preference) {
        // Try if the preference is one of our custom Preferences
        var dialogFragment: DialogFragment? = null
        if (preference is TimePreference) {
            // Create a new instance of TimePreferenceDialogFragment with the key of the related
            // Preference
            dialogFragment = TimePreferenceDialogFragmentCompat
                .newInstance(preference.getKey())
        }

        // If it was one of our custom Preferences, show its dialog
        if (dialogFragment != null) {
            dialogFragment.setTargetFragment(this, 0)
            dialogFragment.show(
                parentFragmentManager,
                "androidx.preference.PreferenceFragment.DIALOG"
            )
        } else {
            // Could not be handled here. Try with the super method.
            super.onDisplayPreferenceDialog(preference)
        }
    }
}