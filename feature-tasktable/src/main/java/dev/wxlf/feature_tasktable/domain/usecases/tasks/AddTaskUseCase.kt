package dev.wxlf.feature_tasktable.domain.usecases.tasks

import dev.wxlf.feature_tasktable.domain.repositories.TasksRepository

class AddTaskUseCase(private val tasksRepository: TasksRepository) {
    suspend fun execute(taskName: String, taskData: String, taskColumnId: Long): Long =
        tasksRepository.addTask(taskName, taskData, taskColumnId)
}