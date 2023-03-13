package dev.wxlf.feature_notes.domain.usecases

import dev.wxlf.feature_notes.data.entities.NoteEntity
import dev.wxlf.feature_notes.domain.repository.NotesRepository

class FetchAllNotesUseCase(private val notesRepository: NotesRepository) {
    suspend fun execute(): List<NoteEntity> =
        notesRepository.fetchAllNotes()
}