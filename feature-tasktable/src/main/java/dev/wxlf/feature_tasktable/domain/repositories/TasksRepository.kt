package dev.wxlf.feature_tasktable.domain.repositories

import dev.wxlf.feature_tasktable.data.entities.TaskEntity

interface TasksRepository {

    suspend fun addTask(taskName: String, taskData: String, taskColumnId: Long): Long
    suspend fun fetchAllTasks(): List<TaskEntity>
    suspend fun fetchTask(id: Long): TaskEntity
    suspend fun updateTask(task: TaskEntity)
    suspend fun deleteTask(task: TaskEntity)
}