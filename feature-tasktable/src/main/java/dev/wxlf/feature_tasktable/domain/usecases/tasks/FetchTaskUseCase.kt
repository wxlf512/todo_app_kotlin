package dev.wxlf.feature_tasktable.domain.usecases.tasks

import dev.wxlf.feature_tasktable.data.entities.TaskEntity
import dev.wxlf.feature_tasktable.domain.repositories.TasksRepository

class FetchTaskUseCase(private val tasksRepository: TasksRepository) {
    suspend fun execute(id: Long): TaskEntity =
        tasksRepository.fetchTask(id)
}