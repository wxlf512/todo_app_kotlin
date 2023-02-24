package dev.wxlf.todoapp.domain.usecases.notes

import dev.wxlf.todoapp.data.entities.NoteEntity
import dev.wxlf.todoapp.domain.repository.NotesRepository

class FetchAllNotesUseCase(private val notesRepository: NotesRepository) {
    suspend fun execute(): List<NoteEntity> =
        notesRepository.fetchAllNotes()
}