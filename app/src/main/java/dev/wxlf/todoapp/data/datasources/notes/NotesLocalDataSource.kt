package dev.wxlf.todoapp.data.datasources.notes

import dev.wxlf.todoapp.data.entities.NoteEntity
import dev.wxlf.todoapp.data.entities.NoteNameAndData

interface NotesLocalDataSource {

    suspend fun insertNote(noteNameAndData: NoteNameAndData) : Long
    suspend fun loadAllNotes() : List<NoteEntity>
    suspend fun loadNote(id: Long) : NoteEntity
    suspend fun updateNote(note: NoteEntity)
    suspend fun deleteNote(note: NoteEntity)
}