package dev.wxlf.todoapp.data.repositories

import dev.wxlf.todoapp.data.datasources.notes.NotesLocalDataSource
import dev.wxlf.todoapp.data.entities.NoteEntity
import dev.wxlf.todoapp.domain.repository.NotesRepository

class NotesRepositoryImpl(private val local: NotesLocalDataSource) : NotesRepository {
    override suspend fun addNote(noteName: String, noteData: String) =
        local.insertNote(noteName, noteData)

    override suspend fun fetchAllNotes(): List<NoteEntity> =
        local.loadAllNotes()

    override suspend fun fetchNote(id: Long): NoteEntity =
        local.loadNote(id)

    override suspend fun updateNote(note: NoteEntity) =
        local.updateNote(note)

    override suspend fun deleteNote(note: NoteEntity) =
        local.deleteNote(note)
}