package dev.wxlf.todoapp.domain.usecases.notes

import dev.wxlf.todoapp.data.entities.NoteEntity
import dev.wxlf.todoapp.domain.repository.NotesRepository

class UpdateNoteUseCase(private val notesRepository: NotesRepository) {
    suspend fun execute(note: NoteEntity) =
        notesRepository.updateNote(note)
}