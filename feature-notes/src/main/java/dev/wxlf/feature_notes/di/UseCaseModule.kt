package dev.wxlf.feature_notes.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.wxlf.feature_notes.domain.repository.NotesRepository
import dev.wxlf.feature_notes.domain.usecases.notes.AddNoteUseCase
import dev.wxlf.feature_notes.domain.usecases.notes.DeleteNoteUseCase
import dev.wxlf.feature_notes.domain.usecases.notes.FetchAllNotesUseCase
import dev.wxlf.feature_notes.domain.usecases.notes.FetchNoteUseCase
import dev.wxlf.feature_notes.domain.usecases.notes.UpdateNoteUseCase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Provides
    @Singleton
    fun providesAddNoteUseCase(notesRepository: NotesRepository) =
        AddNoteUseCase(notesRepository)

    @Provides
    @Singleton
    fun providesDeleteNoteUseCase(notesRepository: NotesRepository) =
        DeleteNoteUseCase(notesRepository)

    @Provides
    @Singleton
    fun providesFetchAllNotesUseCase(notesRepository: NotesRepository) =
        FetchAllNotesUseCase(notesRepository)

    @Provides
    @Singleton
    fun providesFetchNoteUseCase(notesRepository: NotesRepository) =
        FetchNoteUseCase(notesRepository)

    @Provides
    @Singleton
    fun providesUpdateNoteUseCase(notesRepository: NotesRepository) =
        UpdateNoteUseCase(notesRepository)
}