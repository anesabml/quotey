package com.anesabml.quotey.framework.workers

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.anesabml.quotey.core.data.QuoteRepository
import com.anesabml.quotey.framework.RemoteLocalQuoteDataSource
import com.anesabml.quotey.framework.managers.NotificationManger
import com.anesabml.quotey.framework.utils.WorkerUtils

class QodWorker(private val appContext: Context, workerParams: WorkerParameters) :
    CoroutineWorker(appContext, workerParams) {
    override suspend fun doWork(): Result {
        // Get the quote
        val repository = QuoteRepository(RemoteLocalQuoteDataSource(applicationContext))
        val quote = repository.getQod()

        // Show a notification
        val notificationManager = NotificationManger(appContext)
        quote.let { notificationManager.showQodNotification(it) }

        // After schedule the task to run tomorrow
        WorkerUtils.setupWork(appContext)

        return Result.success()
    }
}