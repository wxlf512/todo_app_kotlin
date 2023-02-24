package dev.wxlf.todoapp.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import dev.wxlf.todoapp.data.entities.NoteEntity.Companion.NOTES_TABLE

@Entity(tableName = NOTES_TABLE)
data class NoteEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Long,
    @ColumnInfo(name = "noteName") var noteName: String,
    @ColumnInfo(name = "noteData") var noteData: String,
    @ColumnInfo(name = "createTimestamp") val createTimestamp: String,
    @ColumnInfo(name = "editTimestamp") var editTimestamp: String,
) {
    companion object {
        const val NOTES_TABLE = "notes_table"
    }
}
