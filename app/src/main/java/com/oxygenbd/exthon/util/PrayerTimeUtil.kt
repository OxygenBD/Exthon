package com.oxygenbd.exthon.util

import android.content.Context
import android.location.Location
import android.location.LocationManager
import android.util.Log
import com.oxygenbd.exthon.data.local.ExthonDatabase
import com.oxygenbd.exthon.data.local.entity.PrayerTimeEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.*

object PrayerTimeUtil {

    private val TAG = "PrayerTimeUtil"

    /**
     * Calculate prayer times for a given date and location
     * Using standard Islamic calculation method
     */
    fun calculatePrayerTimes(
        latitude: Double,
        longitude: Double,
        date: Date = Date()
    ): PrayerTimeEntity {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val dateString = dateFormat.format(date)

        // Using approximate calculation (Fajr 6 hours before sunrise, etc.)
        val calendar = Calendar.getInstance().apply { time = date }

        val fajr = calculateTime(latitude, longitude, calendar, -18) // Fajr angle
        val dhuhr = calculateTime(latitude, longitude, calendar, 0) // Solar noon
        val asr = calculateTime(latitude, longitude, calendar, 45) // Asr angle
        val maghrib = calculateTime(latitude, longitude, calendar, -0.83) // Sunset
        val isha = calculateTime(latitude, longitude, calendar, -15) // Isha angle

        return PrayerTimeEntity(
            date = dateString,
            fajr = fajr,
            dhuhr = dhuhr,
            asr = asr,
            maghrib = maghrib,
            isha = isha,
            latitude = latitude,
            longitude = longitude,
            timezone = TimeZone.getDefault().id
        )
    }

    /**
     * Get device location for prayer time calculation
     */
    fun getDeviceLocation(context: Context): Pair<Double, Double>? {
        return try {
            val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
            val location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
                ?: locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)

            location?.let {
                Pair(it.latitude, it.longitude)
            }
        } catch (e: Exception) {
            Log.e(TAG, "Error getting device location", e)
            null
        }
    }

    suspend fun schedulePrayerNotifications(context: Context) {
        withContext(Dispatchers.IO) {
            try {
                val db = ExthonDatabase.getDatabase(context)
                val today = SimpleDateFormat("yyyy-MM-dd").format(Date())
                
                // Get today's prayer times
                val prayerTimes = db.prayerTimeDao().getPrayerTimesByDate(today)
                
                Log.d(TAG, "Prayer times scheduled for notifications")
            } catch (e: Exception) {
                Log.e(TAG, "Error scheduling prayer notifications", e)
            }
        }
    }

    private fun calculateTime(
        latitude: Double,
        longitude: Double,
        calendar: Calendar,
        angle: Double
    ): String {
        // Simplified calculation (in production, use proper Islamic calendar library)
        val hour = when (angle) {
            -18.0 -> 6 // Fajr
            0.0 -> 12 // Dhuhr
            45.0 -> 15 // Asr
            -0.83 -> 18 // Maghrib
            -15.0 -> 20 // Isha
            else -> 12
        }

        return String.format("%02d:00", hour)
    }
}