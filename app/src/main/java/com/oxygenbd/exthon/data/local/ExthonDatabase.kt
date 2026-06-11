package com.oxygenbd.exthon.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.oxygenbd.exthon.data.local.dao.*
import com.oxygenbd.exthon.data.local.entity.*

@Database(
    entities = [
        PlaylistEntity::class,
        VideoEntity::class,
        ScheduleEntity::class,
        NoteEntity::class,
        NoteGroupEntity::class,
        PomodoroSessionEntity::class,
        AchievementEntity::class,
        BlockedSiteEntity::class,
        PrayerTimeEntity::class,
        DailyStatsEntity::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class ExthonDatabase : RoomDatabase() {

    abstract fun playlistDao(): PlaylistDao
    abstract fun videoDao(): VideoDao
    abstract fun scheduleDao(): ScheduleDao
    abstract fun noteDao(): NoteDao
    abstract fun pomodoroDao(): PomodoroDao
    abstract fun achievementDao(): AchievementDao
    abstract fun blockingDao(): BlockingDao
    abstract fun prayerTimeDao(): PrayerTimeDao
    abstract fun dailyStatsDao(): DailyStatsDao

    companion object {
        @Volatile
        private var INSTANCE: ExthonDatabase? = null

        fun getDatabase(context: Context): ExthonDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ExthonDatabase::class.java,
                    "exthon_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}