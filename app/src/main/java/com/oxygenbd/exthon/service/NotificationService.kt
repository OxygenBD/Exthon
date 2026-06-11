package com.oxygenbd.exthon.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import com.oxygenbd.exthon.R
import com.oxygenbd.exthon.ui.MainActivity

object NotificationService {

    private const val CHANNEL_ID_PRAYER = "prayer_times"
    private const val CHANNEL_ID_POMODORO = "pomodoro_timer"
    private const val CHANNEL_ID_REMINDER = "reminders"
    private const val CHANNEL_ID_ACHIEVEMENT = "achievements"
    private const val CHANNEL_ID_BLOCKING = "blocking_alerts"

    fun createNotificationChannels(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            // Prayer times channel
            NotificationChannel(
                CHANNEL_ID_PRAYER,
                "Prayer Times",
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                description = "Notifications for prayer times"
                enableVibration(true)
                notificationManager.createNotificationChannel(this)
            }

            // Pomodoro channel
            NotificationChannel(
                CHANNEL_ID_POMODORO,
                "Pomodoro Timer",
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                description = "Pomodoro timer notifications"
                notificationManager.createNotificationChannel(this)
            }

            // Reminders channel
            NotificationChannel(
                CHANNEL_ID_REMINDER,
                "Reminders",
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                description = "Schedule reminders"
                notificationManager.createNotificationChannel(this)
            }

            // Achievement channel
            NotificationChannel(
                CHANNEL_ID_ACHIEVEMENT,
                "Achievements",
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                description = "Achievement unlocked notifications"
                enableVibration(true)
                notificationManager.createNotificationChannel(this)
            }

            // Blocking channel
            NotificationChannel(
                CHANNEL_ID_BLOCKING,
                "Blocking Alerts",
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                description = "Blocked site/app alerts"
                enableVibration(true)
                notificationManager.createNotificationChannel(this)
            }
        }
    }

    fun showPrayerTimeNotification(context: Context, prayerName: String, time: String) {
        val intent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)

        val notification = NotificationCompat.Builder(context, CHANNEL_ID_PRAYER)
            .setSmallIcon(R.drawable.ic_prayer)
            .setContentTitle("$prayerName - $time")
            .setContentText("Time for $prayerName prayer")
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .build()

        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(prayerName.hashCode(), notification)
    }

    fun showPomodoroNotification(context: Context, category: String, message: String) {
        val intent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)

        val notification = NotificationCompat.Builder(context, CHANNEL_ID_POMODORO)
            .setSmallIcon(R.drawable.ic_pomodoro)
            .setContentTitle("$category Session")
            .setContentText(message)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .build()

        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(category.hashCode() + 100, notification)
    }

    fun showAchievementNotification(context: Context, title: String, description: String) {
        val intent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)

        val notification = NotificationCompat.Builder(context, CHANNEL_ID_ACHIEVEMENT)
            .setSmallIcon(R.drawable.ic_achievement)
            .setContentTitle("🏆 $title")
            .setContentText(description)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .build()

        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(title.hashCode() + 1000, notification)
    }

    fun showBlockingNotification(context: Context, appName: String, category: String) {
        val notification = NotificationCompat.Builder(context, CHANNEL_ID_BLOCKING)
            .setSmallIcon(R.drawable.ic_blocking)
            .setContentTitle("Access Blocked")
            .setContentText("$appName is blocked in $category")
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .build()

        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(appName.hashCode() + 5000, notification)
    }
}