package com.oxygenbd.exthon.service

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.Binder
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import com.oxygenbd.exthon.R
import com.oxygenbd.exthon.data.local.ExthonDatabase
import kotlinx.coroutines.*
import java.text.SimpleDateFormat
import java.util.*

class PomodoroService : Service() {

    private val TAG = "PomodoroService"
    private val binder = LocalBinder()
    private lateinit var db: ExthonDatabase
    private var timerJob: Job? = null
    private var isRunning = false
    private var remainingTime: Long = 0L

    inner class LocalBinder : Binder() {
        fun getService(): PomodoroService = this@PomodoroService
    }

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "Pomodoro Service Created")
        db = ExthonDatabase.getDatabase(this)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(TAG, "Pomodoro Service Started")
        return START_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? = binder

    fun startPomodoro(
        category: String,
        durationMinutes: Int,
        onTick: (Long) -> Unit,
        onComplete: () -> Unit
    ) {
        if (isRunning) return

        isRunning = true
        remainingTime = (durationMinutes * 60 * 1000).toLong()
        
        timerJob = CoroutineScope(Dispatchers.Main).launch {
            while (remainingTime > 0 && isRunning) {
                delay(1000)
                remainingTime -= 1000
                onTick(remainingTime)
            }

            if (isRunning) {
                playCompletionSound()
                onComplete()
                saveSession(category, durationMinutes)
                isRunning = false
            }
        }
    }

    fun pausePomodoro() {
        isRunning = false
    }

    fun resumePomodoro(
        category: String,
        durationMinutes: Int,
        onTick: (Long) -> Unit,
        onComplete: () -> Unit
    ) {
        startPomodoro(category, durationMinutes, onTick, onComplete)
    }

    fun stopPomodoro() {
        timerJob?.cancel()
        isRunning = false
        remainingTime = 0L
    }

    private fun playCompletionSound() {
        try {
            val mediaPlayer = MediaPlayer.create(this, R.raw.pomodoro_complete)
            mediaPlayer?.start()
        } catch (e: Exception) {
            Log.e(TAG, "Error playing sound", e)
        }
    }

    private fun saveSession(category: String, durationMinutes: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                val today = dateFormat.format(Date())
                
                // Save to database
                // db.pomodoroDao().insertSession(...)
                Log.d(TAG, "Pomodoro session saved for $category")
            } catch (e: Exception) {
                Log.e(TAG, "Error saving session", e)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        stopPomodoro()
    }
}