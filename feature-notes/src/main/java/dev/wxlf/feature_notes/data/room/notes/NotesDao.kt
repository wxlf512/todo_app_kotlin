package dev.wxlf.feature_notes.data.room.notes

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import dev.wxlf.feature_notes.data.entities.NoteEntity
import dev.wxlf.feature_notes.data.entities.NoteEntity.Companion.NOTES_TABLE
import dev.wxlf.feature_notes.data.entities.NoteNameAndData

@Dao
interface NotesDao {

//    @Query("INSERT INTO $NOTES_TABLE (noteName, noteData) VALUES(:noteName, :noteData)")
//    suspend fun insertNote(noteName: String, noteData: String)

    @Insert(entity = NoteEntity::class)
    suspend fun insertNote(noteNameAndData: NoteNameAndData): Long

    @Query("SELECT * FROM $NOTES_TABLE")
    suspend fun loadAllNotes(): List<NoteEntity>

    @Query("SELECT * FROM $NOTES_TABLE WHERE id=:id")
    suspend fun loadNote(id: Long): NoteEntity

    @Update(entity = NoteEntity::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateNote(note: NoteEntity)

    @Delete(entity = NoteEntity::class)
    suspend fun deleteNote(note: NoteEntity)
}