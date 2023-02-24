package dev.wxlf.todoapp.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.wxlf.todoapp.data.datasources.notes.NotesLocalDataSource
import dev.wxlf.todoapp.data.datasources.notes.NotesRoomDataSource
import dev.wxlf.todoapp.data.repositories.NotesRepositoryImpl
import dev.wxlf.todoapp.data.room.notes.NotesDatabase
import dev.wxlf.todoapp.domain.repository.NotesRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    @Singleton
    fun provideNotesDatabase(@ApplicationContext context: Context) : NotesDatabase =
        Room.databaseBuilder(
            context = context,
            NotesDatabase::class.java,
            "notes_database"
        ).build()

    @Provides
    @Singleton
    fun provideNotesRoomDataSource(notesDatabase: NotesDatabase) : NotesLocalDataSource =
        NotesRoomDataSource(notesDatabase.notesDao())

    @Provides
    @Singleton
    fun provideNotesRepository(local: NotesLocalDataSource) : NotesRepository =
        NotesRepositoryImpl(local)
}