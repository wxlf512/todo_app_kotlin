package dev.wxlf.feature_notes.data.repositories

import dev.wxlf.feature_notes.data.datasources.NotesLocalDataSource
import dev.wxlf.feature_notes.data.entities.NoteEntity
import dev.wxlf.feature_notes.data.entities.NoteNameAndData
import dev.wxlf.feature_notes.domain.repository.NotesRepository

class NotesRepositoryImpl(private val local: NotesLocalDataSource) : NotesRepository {
    override suspend fun addNote(noteName: String, noteData: String): Long =
        local.insertNote(NoteNameAndData(noteName, noteData))

    override suspend fun fetchAllNotes(): List<NoteEntity> =
        local.loadAllNotes()

    override suspend fun fetchNote(id: Long): NoteEntity =
        local.loadNote(id)

    override suspend fun updateNote(note: NoteEntity) =
        local.updateNote(note)

    override suspend fun deleteNote(note: NoteEntity) =
        local.deleteNote(note)
}