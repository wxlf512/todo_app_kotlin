package dev.wxlf.feature_notes.domain.usecases

import dev.wxlf.feature_notes.domain.repository.NotesRepository

class AddNoteUseCase(private val notesRepository: NotesRepository) {
    suspend fun execute(noteName: String, noteData: String): Long =
        notesRepository.addNote(noteName, noteData)
}