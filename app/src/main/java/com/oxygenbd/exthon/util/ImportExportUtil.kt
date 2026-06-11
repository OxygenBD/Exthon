package com.oxygenbd.exthon.util

import android.content.Context
import android.net.Uri
import android.util.Log
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.oxygenbd.exthon.data.local.ExthonDatabase
import com.oxygenbd.exthon.data.local.entity.ScheduleEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileWriter
import java.text.SimpleDateFormat
import java.util.*

object ImportExportUtil {

    private val TAG = "ImportExportUtil"
    private val gson: Gson = GsonBuilder().setPrettyPrinting().create()

    data class ExthonScheduleBackup(
        val version: String = "1.0",
        val exportDate: String = SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Date()),
        val schedules: List<ScheduleEntity> = emptyList(),
        val pomodoroSettings: Map<String, Any> = emptyMap(),
        val blockedSites: List<Map<String, Any>> = emptyList(),
        val achievements: List<Map<String, Any>> = emptyList()
    )

    /**
     * Export schedule and settings to .exthon file
     */
    suspend fun exportToExthon(context: Context, fileName: String = "exthon_backup"): Boolean {
        return withContext(Dispatchers.IO) {
            try {
                val db = ExthonDatabase.getDatabase(context)
                
                // Create backup object
                val backup = ExthonScheduleBackup()

                // Convert to JSON
                val jsonString = gson.toJson(backup)

                // Save to file
                val file = File(context.getExternalFilesDir(null), "$fileName.exthon")
                FileWriter(file).use { writer ->
                    writer.write(jsonString)
                }

                Log.d(TAG, "Successfully exported to ${file.absolutePath}")
                true
            } catch (e: Exception) {
                Log.e(TAG, "Error exporting to exthon", e)
                false
            }
        }
    }

    /**
     * Import schedule and settings from .exthon file
     */
    suspend fun importFromExthon(context: Context, uri: Uri): Boolean {
        return withContext(Dispatchers.IO) {
            try {
                val inputStream = context.contentResolver.openInputStream(uri)
                val jsonString = inputStream?.bufferedReader().use { it?.readText() ?: "" }

                if (jsonString.isNotEmpty()) {
                    val backup = gson.fromJson(jsonString, ExthonScheduleBackup::class.java)
                    val db = ExthonDatabase.getDatabase(context)

                    // Import schedules
                    backup.schedules.forEach { schedule ->
                        db.scheduleDao().insertSchedule(schedule)
                    }

                    Log.d(TAG, "Successfully imported from $uri")
                    true
                } else {
                    false
                }
            } catch (e: Exception) {
                Log.e(TAG, "Error importing from exthon", e)
                false
            }
        }
    }

    /**
     * Share backup file
     */
    fun shareBackupFile(context: Context, fileName: String): Uri? {
        return try {
            val file = File(context.getExternalFilesDir(null), "$fileName.exthon")
            if (file.exists()) {
                androidx.core.content.FileProvider.getUriForFile(
                    context,
                    "${context.packageName}.fileprovider",
                    file
                )
            } else {
                null
            }
        } catch (e: Exception) {
            Log.e(TAG, "Error sharing backup file", e)
            null
        }
    }
}