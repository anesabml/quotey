package com.anesabml.quotey.ui.settings

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.widget.TextView
import androidx.preference.DialogPreference
import androidx.preference.PreferenceViewHolder
import com.anesabml.quotey.R

private const val dialogLayoutResId = R.layout.pref_time_picker

class TimePreference @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defAttr: Int = dialogLayoutResId,
    defStyle: Int = R.layout.pref_time_picker
) : DialogPreference(context, attrs, defAttr, defStyle) {

    private var mTime: Int = 0

    init {
        positiveButtonText = context.getString(R.string.set)
        negativeButtonText = context.getString(android.R.string.cancel)
    }

    fun getTime(): Int {
        return mTime
    }

    fun setTime(time: Int) {
        mTime = time
        // Save to Shared Preferences
        persistInt(time)
    }

    override fun onBindViewHolder(holder: PreferenceViewHolder?) {
        super.onBindViewHolder(holder)

        val textView = holder?.itemView?.findViewById<TextView>(android.R.id.title)
        textView?.textSize = 16f
    }

    override fun onGetDefaultValue(a: TypedArray, index: Int): Any {
        // Default value from attribute. Fallback value is set to 0.
        return a.getInt(index, 0)
    }

    override fun onSetInitialValue(restorePersistedValue: Boolean, defaultValue: Any?) {
        // Read the value. Use the default value if it is not possible.
        setTime(
            if (restorePersistedValue)
                getPersistedInt(mTime)
            else
                defaultValue as Int
        )
    }

    override fun getDialogLayoutResource(): Int {
        return dialogLayoutResId
    }
}