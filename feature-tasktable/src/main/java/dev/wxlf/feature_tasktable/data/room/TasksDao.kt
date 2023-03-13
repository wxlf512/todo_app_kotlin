package dev.wxlf.feature_tasktable.data.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import dev.wxlf.feature_tasktable.data.entities.TaskEntity
import dev.wxlf.feature_tasktable.data.entities.TaskEntity.Companion.TASKS_TABLE
import dev.wxlf.feature_tasktable.data.entities.TaskNameDataAndId

@Dao
interface TasksDao {

    @Insert(entity = TaskEntity::class)
    suspend fun insertTask(taskNameDataAndId: TaskNameDataAndId): Long

    @Query("SELECT * FROM $TASKS_TABLE")
    suspend fun loadAllTasks(): List<TaskEntity>

    @Query("SELECT * FROM $TASKS_TABLE WHERE id=:id")
    suspend fun loadTask(id: Long): TaskEntity

    @Update(entity = TaskEntity::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateTask(task: TaskEntity)

    @Delete(entity = TaskEntity::class)
    suspend fun deleteTask(task: TaskEntity)
}