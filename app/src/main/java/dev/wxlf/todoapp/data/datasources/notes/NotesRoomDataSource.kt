package dev.wxlf.todoapp.data.datasources.notes

import dev.wxlf.todoapp.data.entities.NoteEntity
import dev.wxlf.todoapp.data.room.notes.NotesDao

class NotesRoomDataSource(private val notesDao: NotesDao) : NotesLocalDataSource {
    override suspend fun insertNote(noteName: String, noteData: String) =
        notesDao.insertNote(noteName, noteData)

    override suspend fun loadAllNotes(): List<NoteEntity> =
        notesDao.loadAllNotes()

    override suspend fun loadNote(id: Long): NoteEntity =
        notesDao.loadNote(id)

    override suspend fun updateNote(note: NoteEntity) =
        notesDao.updateNote(note)

    override suspend fun deleteNote(note: NoteEntity) =
        notesDao.deleteNote(note)
}