package com.anesabml.quotey.workers

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.anesabml.quotey.data.QuoteDataSource
import com.anesabml.quotey.data.QuoteRepository
import com.anesabml.quotey.data.api.ApiService
import com.anesabml.quotey.data.db.QuoteyDatabase
import com.anesabml.quotey.managers.MyNotificationManager
import com.anesabml.quotey.utils.WorkerUtils

class QodWorker(private val appContext: Context, workerParams: WorkerParameters) :
    CoroutineWorker(appContext, workerParams) {
    override suspend fun doWork(): Result {

        // Get the quote
        val database = QuoteyDatabase.getInstance(applicationContext)
        val apiService = ApiService
        val repository = QuoteRepository(
            QuoteDataSource(
                database, apiService
            )
        )
        val quote = repository.getQod()

        // Show a notification
        val notificationManager = MyNotificationManager(appContext)
        quote.let { notificationManager.showQodNotification(it) }

        return Result.success()
    }
}