package dev.wxlf.feature_notes.data.datasources.notes

import dev.wxlf.feature_notes.data.entities.NoteEntity
import dev.wxlf.feature_notes.data.entities.NoteNameAndData
import dev.wxlf.feature_notes.data.room.notes.NotesDao

class NotesRoomDataSource(private val notesDao: NotesDao) : NotesLocalDataSource {
    override suspend fun insertNote(noteNameAndData: NoteNameAndData): Long =
        notesDao.insertNote(noteNameAndData)

    override suspend fun loadAllNotes(): List<NoteEntity> =
        notesDao.loadAllNotes()

    override suspend fun loadNote(id: Long): NoteEntity =
        notesDao.loadNote(id)

    override suspend fun updateNote(note: NoteEntity) =
        notesDao.updateNote(note)

    override suspend fun deleteNote(note: NoteEntity) =
        notesDao.deleteNote(note)
}