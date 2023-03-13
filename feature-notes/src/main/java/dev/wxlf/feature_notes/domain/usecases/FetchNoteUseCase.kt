package dev.wxlf.feature_notes.domain.usecases

import dev.wxlf.feature_notes.data.entities.NoteEntity
import dev.wxlf.feature_notes.domain.repository.NotesRepository

class FetchNoteUseCase(private val notesRepository: NotesRepository) {
    suspend fun execute(id: Long): NoteEntity =
        notesRepository.fetchNote(id)
}