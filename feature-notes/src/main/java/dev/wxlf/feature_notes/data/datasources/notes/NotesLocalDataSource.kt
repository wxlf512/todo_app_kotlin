package dev.wxlf.feature_notes.data.datasources.notes

import dev.wxlf.feature_notes.data.entities.NoteEntity
import dev.wxlf.feature_notes.data.entities.NoteNameAndData

interface NotesLocalDataSource {

    suspend fun insertNote(noteNameAndData: NoteNameAndData) : Long
    suspend fun loadAllNotes() : List<NoteEntity>
    suspend fun loadNote(id: Long) : NoteEntity
    suspend fun updateNote(note: NoteEntity)
    suspend fun deleteNote(note: NoteEntity)
}