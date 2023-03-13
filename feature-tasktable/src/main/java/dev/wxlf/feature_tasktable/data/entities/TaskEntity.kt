package dev.wxlf.feature_tasktable.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import dev.wxlf.feature_tasktable.data.entities.TaskEntity.Companion.TASKS_TABLE

@Entity(tableName = TASKS_TABLE)
data class TaskEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Long,
    @ColumnInfo(name = "taskName") var taskName: String,
    @ColumnInfo(name = "taskData") var taskData: String,
    @ColumnInfo(name = "taskColumnId") var taskColumnId: Long,
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
        const val TASKS_TABLE = "tasks_table"
    }
}

data class TaskNameDataAndId(
    var taskName: String,
    var taskData: String,
    var taskColumnId: Long
)