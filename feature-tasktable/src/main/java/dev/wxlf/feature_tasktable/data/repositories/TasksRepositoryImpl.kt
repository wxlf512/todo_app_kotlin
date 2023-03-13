package dev.wxlf.feature_tasktable.data.repositories

import dev.wxlf.feature_tasktable.data.datasources.tasks.TasksLocalDataSource
import dev.wxlf.feature_tasktable.data.entities.TaskEntity
import dev.wxlf.feature_tasktable.data.entities.TaskNameDataAndId
import dev.wxlf.feature_tasktable.domain.repositories.TasksRepository

class TasksRepositoryImpl(private val local: TasksLocalDataSource) : TasksRepository {
    override suspend fun addTask(taskName: String, taskData: String, taskColumnId: Long): Long =
        local.insertTask(TaskNameDataAndId(taskName, taskData, taskColumnId))

    override suspend fun fetchAllTasks(): List<TaskEntity> =
        local.loadAllTasks()

    override suspend fun fetchTask(id: Long): TaskEntity =
        local.loadTask(id)

    override suspend fun updateTask(task: TaskEntity) =
        local.updateTask(task)

    override suspend fun deleteTask(task: TaskEntity) =
        local.deleteTask(task)
}