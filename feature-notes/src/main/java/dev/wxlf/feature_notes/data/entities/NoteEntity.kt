package dev.wxlf.feature_notes.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import dev.wxlf.feature_notes.data.entities.NoteEntity.Companion.NOTES_TABLE

@Entity(tableName = NOTES_TABLE)
data class NoteEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Long,
    @ColumnInfo(name = "noteName") var noteName: String,
    @ColumnInfo(name = "noteData") var noteData: String,
    @ColumnInfo(
        name = "createTimestamp",
        defaultValue = "CURRENT_TIMESTAMP"
    ) val createTimestamp: String,
    @ColumnInfo(
        name = "editTimestamp",
        defaultValue = "CURRENT_TIMESTAMP"
    ) var editTimestamp: String
) {
    companion object {
        const val NOTES_TABLE = "notes_table"
    }
}

data class NoteNameAndData(
    var noteName: String,
    var noteData: String
)