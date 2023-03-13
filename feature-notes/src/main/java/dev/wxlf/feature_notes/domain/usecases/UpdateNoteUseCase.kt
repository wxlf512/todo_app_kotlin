package dev.wxlf.feature_notes.domain.usecases

import dev.wxlf.feature_notes.data.entities.NoteEntity
import dev.wxlf.feature_notes.domain.repository.NotesRepository

class UpdateNoteUseCase(private val notesRepository: NotesRepository) {
    suspend fun execute(note: NoteEntity) =
        notesRepository.updateNote(note)
}