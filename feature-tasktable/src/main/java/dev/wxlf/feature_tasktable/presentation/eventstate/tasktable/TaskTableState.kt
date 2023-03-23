package dev.wxlf.feature_tasktable.presentation.eventstate.tasktable

import dev.wxlf.feature_tasktable.data.entities.ColumnEntity
import dev.wxlf.feature_tasktable.data.entities.TaskEntity

sealed class TaskTableState {
    object LoadingState : TaskTableState()
    data class LoadedState(val columns: List<ColumnEntity>, val tasks: List<TaskEntity>) :
        TaskTableState()
    data class ErrorState(val msg: String) : TaskTableState()
}