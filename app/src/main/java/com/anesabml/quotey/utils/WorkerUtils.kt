package com.anesabml.quotey.utils

import android.content.Context
import androidx.preference.PreferenceManager
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import com.anesabml.quotey.workers.QodWorker
import java.util.*
import java.util.concurrent.TimeUnit

object WorkerUtils {

    private const val WORK_TAG = "qod_worker"

    fun setupWork(context: Context) {

        // Get the time set by the user
        // Default value is 8:00 AM
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)

        // Get the days and hours
        val minutesAfterMidnight = sharedPreferences.getInt("time", 480)
        val days = sharedPreferences.getStringSet("days", setOf())

        val hours = minutesAfterMidnight / 60
        val minutes = minutesAfterMidnight % 60

        scheduleWorkAt(context, days!!, hours, minutes)

    }

    private fun scheduleWorkAt(context: Context, days: Set<String>, hour: Int, minute: Int) {

        val calendar = Calendar.getInstance()
        // Get the current time
        val nowMillis = calendar.timeInMillis

        // Check if we can't schedule a notification for today,
        // if so schedule it for the next day
        if (
            calendar.get(Calendar.HOUR_OF_DAY) > hour ||
            calendar.get(Calendar.HOUR_OF_DAY) == hour &&
            calendar.get(Calendar.MINUTE) + 1 >= minute
        ) {
            calendar.add(Calendar.DAY_OF_MONTH, 1)
        }

        // Set the calender to the specified time by the user
        calendar.set(Calendar.HOUR_OF_DAY, hour)
        calendar.set(Calendar.MINUTE, minute)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)

        val workManager = WorkManager.getInstance(context)

        // Cancel any previous works
        workManager.cancelAllWorkByTag(WORK_TAG)

        days.forEach { day ->

            val dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK)
            val dayDifference = (day.toInt() - dayOfWeek + 7) % 7

            // Set the calender to the specified day
            calendar.add(Calendar.DAY_OF_MONTH, dayDifference)

            val delay = calendar.timeInMillis - nowMillis

            // Must have internet connection
            val constraints = Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build()

            // Schedule the work
            val qodWork = OneTimeWorkRequest.Builder(QodWorker::class.java)
                .setConstraints(constraints)
                .setInitialDelay(delay, TimeUnit.MILLISECONDS)
                .addTag(WORK_TAG)
                .build()

            // Hand off the task to the system
            workManager.enqueue(qodWork)

        }

    }
}