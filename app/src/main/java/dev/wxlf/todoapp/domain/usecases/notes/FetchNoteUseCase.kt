package dev.wxlf.todoapp.domain.usecases.notes

import dev.wxlf.todoapp.data.entities.NoteEntity
import dev.wxlf.todoapp.domain.repository.NotesRepository

class FetchNoteUseCase(private val notesRepository: NotesRepository) {
    suspend fun execute(id: Long): NoteEntity =
        notesRepository.fetchNote(id)
}