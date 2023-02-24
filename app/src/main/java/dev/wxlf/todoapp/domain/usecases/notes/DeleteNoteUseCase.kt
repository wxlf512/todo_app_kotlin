package dev.wxlf.todoapp.domain.usecases.notes

import dev.wxlf.todoapp.data.entities.NoteEntity
import dev.wxlf.todoapp.domain.repository.NotesRepository

class DeleteNoteUseCase(private val notesRepository: NotesRepository) {
    suspend fun execute(note: NoteEntity) =
        notesRepository.deleteNote(note)
}