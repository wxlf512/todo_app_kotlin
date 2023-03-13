package dev.wxlf.feature_tasktable.data.datasources.tasks

import dev.wxlf.feature_tasktable.data.entities.TaskEntity
import dev.wxlf.feature_tasktable.data.entities.TaskNameDataAndId
import dev.wxlf.feature_tasktable.data.room.TasksDao

class TasksRoomDataSource(private val tasksDao: TasksDao) : TasksLocalDataSource {
    override suspend fun insertTask(taskNameDataAndId: TaskNameDataAndId): Long =
        tasksDao.insertTask(taskNameDataAndId)

    override suspend fun loadAllTasks(): List<TaskEntity> =
        tasksDao.loadAllTasks()

    override suspend fun loadTask(id: Long): TaskEntity =
        tasksDao.loadTask(id)

    override suspend fun updateTask(task: TaskEntity) =
        tasksDao.updateTask(task)

    override suspend fun deleteTask(task: TaskEntity) =
        tasksDao.deleteTask(task)
}