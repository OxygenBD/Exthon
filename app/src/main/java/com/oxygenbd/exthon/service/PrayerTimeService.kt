package com.oxygenbd.exthon.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import androidx.work.*
import com.oxygenbd.exthon.data.local.ExthonDatabase
import com.oxygenbd.exthon.util.PrayerTimeUtil
import kotlinx.coroutines.*
import java.util.concurrent.TimeUnit

class PrayerTimeService : Service() {

    private val TAG = "PrayerTimeService"
    private lateinit var db: ExthonDatabase
    private val serviceScope = CoroutineScope(Dispatchers.Main + Job())

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "Prayer Time Service Created")
        db = ExthonDatabase.getDatabase(this)
        schedulePrayerTimeChecks()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(TAG, "Prayer Time Service Started")
        return START_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onDestroy() {
        super.onDestroy()
        serviceScope.cancel()
    }

    private fun schedulePrayerTimeChecks() {
        // Schedule periodic prayer time checks
        val prayerTimeWorkRequest = PeriodicWorkRequestBuilder<PrayerTimeWorker>(
            15, TimeUnit.MINUTES
        ).build()

        WorkManager.getInstance(this).enqueueUniquePeriodicWork(
            "prayer_time_check",
            ExistingPeriodicWorkPolicy.KEEP,
            prayerTimeWorkRequest
        )

        Log.d(TAG, "Prayer time checks scheduled")
    }
}

class PrayerTimeWorker(
    context: android.content.Context,
    params: WorkerParameters
) : CoroutineWorker(context, params) {

    override suspend fun doWork(): Result = withContext(Dispatchers.IO) {
        return@withContext try {
            val db = ExthonDatabase.getDatabase(applicationContext)
            val today = java.text.SimpleDateFormat("yyyy-MM-dd").format(java.util.Date())
            
            // Fetch prayer times
            val prayerTimes = db.prayerTimeDao().getPrayerTimesByDate(today)
            
            // Schedule notifications
            PrayerTimeUtil.schedulePrayerNotifications(applicationContext)
            
            Result.success()
        } catch (e: Exception) {
            Log.e("PrayerTimeWorker", "Error in prayer time worker", e)
            Result.retry()
        }
    }
}