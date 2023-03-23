package dev.wxlf.feature_tasktable.presentation.viewmodels

import android.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.wxlf.feature_tasktable.domain.usecases.columns.AddColumnUseCase
import dev.wxlf.feature_tasktable.domain.usecases.columns.DeleteColumnUseCase
import dev.wxlf.feature_tasktable.domain.usecases.columns.FetchAllColumnsUseCase
import dev.wxlf.feature_tasktable.domain.usecases.tasks.AddTaskUseCase
import dev.wxlf.feature_tasktable.domain.usecases.tasks.DeleteTaskUseCase
import dev.wxlf.feature_tasktable.domain.usecases.tasks.FetchAllTasksUseCase
import dev.wxlf.feature_tasktable.presentation.eventstate.tasktable.TaskTableEvent
import dev.wxlf.feature_tasktable.presentation.eventstate.tasktable.TaskTableState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class TaskTableViewModel @Inject constructor(
    private val fetchAllColumnsUseCase: FetchAllColumnsUseCase,
    private val fetchAllTasksUseCase: FetchAllTasksUseCase,
    private val deleteColumnUseCase: DeleteColumnUseCase,
    private val deleteTaskUseCase: DeleteTaskUseCase,

    // TEST
    private val addColumnUseCase: AddColumnUseCase,
    private val addTaskUseCase: AddTaskUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow<TaskTableState>(TaskTableState.LoadingState)
    val uiState: StateFlow<TaskTableState> = _uiState.asStateFlow()

    fun obtainEvent(event: TaskTableEvent) {
        when (event) {
            is TaskTableEvent.DeleteColumn -> {
                viewModelScope.launch(Dispatchers.IO) {
                    deleteColumnUseCase.execute(event.column)
                    val tasks = fetchAllTasksUseCase.execute()
                    tasks.forEach { task ->
                        if (task.taskColumnId == event.column.id) {
                            deleteTaskUseCase.execute(task)
                        }
                    }
                    obtainEvent(TaskTableEvent.LoadTasks)
                }
            }

            is TaskTableEvent.DeleteTask -> {
                viewModelScope.launch(Dispatchers.IO) {
                    deleteTaskUseCase.execute(event.task)
                    obtainEvent(TaskTableEvent.LoadTasks)
                }
            }

            TaskTableEvent.LoadTasks -> {
                _uiState.value = TaskTableState.LoadingState
                viewModelScope.launch(Dispatchers.IO) {
                    try {
                        val columns = fetchAllColumnsUseCase.execute()
                        val tasks = fetchAllTasksUseCase.execute()
                        _uiState.emit(TaskTableState.LoadedState(columns, tasks))
                    } catch (e: Exception) {
                        _uiState.emit(TaskTableState.ErrorState(e.localizedMessage.orEmpty()))
                    }
                }
            }

            TaskTableEvent.AddColumn -> {
                viewModelScope.launch(Dispatchers.IO) {
                    try {
                        addColumnUseCase.execute(
                            "Test Column",
                            "#" + Integer.toHexString(
                                Color.argb(
                                    255,
                                    Random.nextInt(256),
                                    Random.nextInt(256),
                                    Random.nextInt(256)
                                )
                            )
                        )
                        obtainEvent(TaskTableEvent.LoadTasks)
                    } catch (e: Exception) {
                        _uiState.emit(TaskTableState.ErrorState(e.localizedMessage.orEmpty()))
                        obtainEvent(TaskTableEvent.LoadTasks)
                    }
                }
            }
            is TaskTableEvent.AddTask -> {
                viewModelScope.launch(Dispatchers.IO) {
                    try {
                        addTaskUseCase.execute("TestTask", "TestData", event.columnId)
                        obtainEvent(TaskTableEvent.LoadTasks)
                    } catch (e: Exception) {
                        _uiState.emit(TaskTableState.ErrorState(e.localizedMessage.orEmpty()))
                        obtainEvent(TaskTableEvent.LoadTasks)
                    }
                }
            }
        }
    }
}