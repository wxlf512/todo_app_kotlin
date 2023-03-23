package dev.wxlf.feature_tasktable.presentation.eventstate.tasktable

import dev.wxlf.feature_tasktable.data.entities.ColumnEntity
import dev.wxlf.feature_tasktable.data.entities.TaskEntity

sealed class TaskTableEvent {
    object LoadTasks : TaskTableEvent()
    data class DeleteColumn(val column: ColumnEntity) : TaskTableEvent()
    data class DeleteTask(val task: TaskEntity) : TaskTableEvent()

    // TEST
    object AddColumn : TaskTableEvent()
    data class AddTask(val columnId: Long) : TaskTableEvent()
}
