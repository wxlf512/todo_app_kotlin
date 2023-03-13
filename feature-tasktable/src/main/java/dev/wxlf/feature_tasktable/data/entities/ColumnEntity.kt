package dev.wxlf.feature_tasktable.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import dev.wxlf.feature_tasktable.data.entities.ColumnEntity.Companion.COLUMNS_TABLE

@Entity(tableName = COLUMNS_TABLE)
data class ColumnEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Long,
    @ColumnInfo(name = "columnName") var columnName: String,
    @ColumnInfo(name = "columnColor") var columnColor: String
) {
    companion object {
        const val COLUMNS_TABLE = "columns_table"
    }
}

data class ColumnNameAndColor(
    var columnName: String,
    var columnColor: String
)
