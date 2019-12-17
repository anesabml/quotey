package com.anesabml.quotey.framework.managers

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.anesabml.quotey.ui.MainActivity
import com.anesabml.quotey.R
import com.anesabml.quotey.core.domain.Quote

class MyNotificationManager(private val context: Context) {

    private val quoteChannelId = "quote"
    private val quoteChannelName = context.getString(R.string.quote)
    private val quoteChannelDescription = context.getString(R.string.quote_description)
    private val quoteNotificationId = 1

    private val notificationManager: NotificationManagerCompat by lazy {
        NotificationManagerCompat.from(context)
    }


    fun showQodNotification(quote: Quote) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationManager.createNotificationChannel(
                NotificationChannel(
                    quoteChannelId, quoteChannelName, NotificationManager.IMPORTANCE_DEFAULT
                ).apply { description = quoteChannelDescription }
            )
        }

        // Create an explicit intent for an Activity in your app
        val intent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent: PendingIntent = PendingIntent.getActivity(context, 0, intent, 0)


        val builder = NotificationCompat.Builder(context, quoteChannelId)
            .setSmallIcon(R.drawable.ic_notification_icon)
            .setContentTitle(context.getString(R.string.quote_of_the_day))
            .setContentText(quote.title)
            .setStyle(
                NotificationCompat.BigTextStyle()
                    .bigText(quote.quote)
            )
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)


        notificationManager.notify(quoteNotificationId, builder.build())

    }
}