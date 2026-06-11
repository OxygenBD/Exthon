package com.oxygenbd.exthon.di

import android.content.Context
import com.oxygenbd.exthon.data.local.ExthonDatabase
import com.oxygenbd.exthon.data.repository.*
import com.oxygenbd.exthon.domain.repository.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideExthonDatabase(@ApplicationContext context: Context): ExthonDatabase {
        return ExthonDatabase.getDatabase(context)
    }
}

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun providePlaylistRepository(
        db: ExthonDatabase
    ): PlaylistRepository {
        return PlaylistRepositoryImpl(db.playlistDao(), db.videoDao())
    }

    @Singleton
    @Provides
    fun provideScheduleRepository(
        db: ExthonDatabase
    ): ScheduleRepository {
        return ScheduleRepositoryImpl(db.scheduleDao())
    }

    @Singleton
    @Provides
    fun provideNoteRepository(
        db: ExthonDatabase
    ): NoteRepository {
        return NoteRepositoryImpl(db.noteDao())
    }

    @Singleton
    @Provides
    fun providePomodoroRepository(
        db: ExthonDatabase
    ): PomodoroRepository {
        return PomodoroRepositoryImpl(db.pomodoroDao())
    }

    @Singleton
    @Provides
    fun provideAchievementRepository(
        db: ExthonDatabase
    ): AchievementRepository {
        return AchievementRepositoryImpl(db.achievementDao())
    }

    @Singleton
    @Provides
    fun provideBlockingRepository(
        db: ExthonDatabase
    ): BlockingRepository {
        return BlockingRepositoryImpl(db.blockingDao())
    }
}
