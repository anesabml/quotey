package com.anesabml.quotey.framework.utils

import android.content.Context
import androidx.preference.PreferenceManager
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import com.anesabml.quotey.framework.workers.QodWorker
import java.util.*
import java.util.concurrent.TimeUnit

object WorkerUtils {

    private const val WORK_TAG = "qod_worker"

    fun setupWork(context: Context) {

        // Get the time set by the user
        // Default value is 8:00 AM
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        val minutesAfterMidnight = sharedPreferences.getInt("time", 480)

        val hours = minutesAfterMidnight / 60
        val minutes = minutesAfterMidnight % 60

        scheduleWork(context, hours, minutes)
    }

    private fun scheduleWork(context: Context, hour: Int, minute: Int) {
        val calendar = Calendar.getInstance()
        val nowMillis = calendar.timeInMillis

        if (
            calendar.get(Calendar.HOUR_OF_DAY) > hour ||
            calendar.get(Calendar.HOUR_OF_DAY) == hour &&
            calendar.get(Calendar.MINUTE) + 1 >= minute
        ) {
            calendar.add(Calendar.DAY_OF_MONTH, 1)
        }

        calendar.set(Calendar.HOUR_OF_DAY, hour)
        calendar.set(Calendar.MINUTE, minute)

        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)

        val diff = calendar.timeInMillis - nowMillis

        val workManager = WorkManager.getInstance(context)

        // Must have internet connection
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        // Cancel any previous works
        workManager.cancelAllWorkByTag(WORK_TAG)

        val qodWork = OneTimeWorkRequest.Builder(QodWorker::class.java)
            .setConstraints(constraints)
            .setInitialDelay(diff, TimeUnit.MILLISECONDS)
            .addTag(WORK_TAG)
            .build()

        // Hand off the task to the system
        workManager.enqueue(qodWork)
    }
}