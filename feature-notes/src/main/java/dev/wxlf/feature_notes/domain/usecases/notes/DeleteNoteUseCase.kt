package dev.wxlf.feature_notes.domain.usecases.notes

import dev.wxlf.feature_notes.data.entities.NoteEntity
import dev.wxlf.feature_notes.domain.repository.NotesRepository

class DeleteNoteUseCase(private val notesRepository: NotesRepository) {
    suspend fun execute(note: NoteEntity) =
        notesRepository.deleteNote(note)
}