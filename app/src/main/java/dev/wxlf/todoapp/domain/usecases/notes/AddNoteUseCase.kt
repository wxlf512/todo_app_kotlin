package dev.wxlf.todoapp.domain.usecases.notes

import dev.wxlf.todoapp.domain.repository.NotesRepository

class AddNoteUseCase(private val notesRepository: NotesRepository) {
    suspend fun execute(noteName: String, noteData: String): Long =
        notesRepository.addNote(noteName, noteData)
}