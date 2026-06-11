package com.oxygenbd.exthon

import android.app.Application
import com.oxygenbd.exthon.service.NotificationService
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ExthonApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        
        // Initialize notification channels
        NotificationService.createNotificationChannels(this)
        
        // Initialize other services
        initializeServices()
    }

    private fun initializeServices() {
        // Initialize background services, workers, etc.
    }
}
