package dev.wxlf.feature_tasktable.domain.usecases.tasks

import dev.wxlf.feature_tasktable.data.entities.TaskEntity
import dev.wxlf.feature_tasktable.domain.repositories.TasksRepository

class FetchAllTasksUseCase(private val tasksRepository: TasksRepository) {
    suspend fun execute(): List<TaskEntity> =
        tasksRepository.fetchAllTasks()
}