package dev.wxlf.todoapp.data.datasources.notes

import dev.wxlf.todoapp.data.entities.NoteEntity

interface NotesLocalDataSource {

    suspend fun insertNote(noteName: String, noteData: String)
    suspend fun loadAllNotes() : List<NoteEntity>
    suspend fun loadNote(id: Long) : NoteEntity
    suspend fun updateNote(note: NoteEntity)
    suspend fun deleteNote(note: NoteEntity)
}