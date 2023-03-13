package dev.wxlf.feature_tasktable.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import dev.wxlf.feature_tasktable.data.entities.ColumnEntity
import dev.wxlf.feature_tasktable.data.entities.TaskEntity

@Database(
    entities = [ColumnEntity::class, TaskEntity::class],
    version = 1,
    exportSchema = true
)
abstract class TasksDatabase : RoomDatabase() {
    abstract fun columnsDao(): ColumnsDao
    abstract fun tasksDao(): TasksDao
}