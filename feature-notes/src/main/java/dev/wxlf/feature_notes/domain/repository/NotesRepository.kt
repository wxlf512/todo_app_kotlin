package dev.wxlf.feature_notes.domain.repository

import dev.wxlf.feature_notes.data.entities.NoteEntity

interface NotesRepository {

    suspend fun addNote(noteName: String, noteData: String) : Long
    suspend fun fetchAllNotes() : List<NoteEntity>
    suspend fun fetchNote(id: Long) : NoteEntity
    suspend fun updateNote(note: NoteEntity)
    suspend fun deleteNote(note: NoteEntity)
}