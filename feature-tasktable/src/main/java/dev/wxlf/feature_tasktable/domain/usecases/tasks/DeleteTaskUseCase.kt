package dev.wxlf.feature_tasktable.domain.usecases.tasks

import dev.wxlf.feature_tasktable.data.entities.TaskEntity
import dev.wxlf.feature_tasktable.domain.repositories.TasksRepository

class DeleteTaskUseCase(private val tasksRepository: TasksRepository) {
    suspend fun execute(task: TaskEntity) =
        tasksRepository.deleteTask(task)
}