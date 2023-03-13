package dev.wxlf.feature_tasktable.data.datasources.tasks

import dev.wxlf.feature_tasktable.data.entities.TaskEntity
import dev.wxlf.feature_tasktable.data.entities.TaskNameDataAndId

interface TasksLocalDataSource {

    suspend fun insertTask(taskNameDataAndId: TaskNameDataAndId): Long
    suspend fun loadAllTasks(): List<TaskEntity>
    suspend fun loadTask(id: Long): TaskEntity
    suspend fun updateTask(task: TaskEntity)
    suspend fun deleteTask(task: TaskEntity)
}