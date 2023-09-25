package com.derra.notesapp.di

import android.app.Application
import androidx.room.Room
import com.derra.notesapp.data.NoteDatabase
import com.derra.notesapp.data.NoteRepository
import com.derra.notesapp.data.NoteRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideNoteDatabase(app: Application): NoteDatabase{
        return Room.databaseBuilder(
            app,
            NoteDatabase::class.java,
            "note.db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideTodoRepository(db: NoteDatabase) : NoteRepository {
        return NoteRepositoryImpl(db.dao)
    }
}