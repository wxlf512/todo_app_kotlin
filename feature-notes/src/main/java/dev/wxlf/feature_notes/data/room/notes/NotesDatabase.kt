package dev.wxlf.feature_notes.data.room.notes

import androidx.room.Database
import androidx.room.RoomDatabase
import dev.wxlf.feature_notes.data.entities.NoteEntity

@Database(
    entities = [NoteEntity::class],
    version = 1,
    exportSchema = true
)
abstract class NotesDatabase : RoomDatabase() {
    abstract fun notesDao(): NotesDao
}