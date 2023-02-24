package dev.wxlf.todoapp.domain.repository

import dev.wxlf.todoapp.data.entities.NoteEntity

interface NotesRepository {

    suspend fun addNote(noteName: String, noteData: String)
    suspend fun fetchAllNotes() : List<NoteEntity>
    suspend fun fetchNote(id: Long) : NoteEntity
    suspend fun updateNote(note: NoteEntity)
    suspend fun deleteNote(note: NoteEntity)
}