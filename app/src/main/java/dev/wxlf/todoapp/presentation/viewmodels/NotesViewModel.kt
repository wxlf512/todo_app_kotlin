package dev.wxlf.todoapp.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.wxlf.todoapp.data.entities.NoteEntity
import dev.wxlf.todoapp.domain.usecases.notes.DeleteNoteUseCase
import dev.wxlf.todoapp.domain.usecases.notes.FetchAllNotesUseCase
import dev.wxlf.todoapp.presentation.eventstate.notes.NotesEvent
import dev.wxlf.todoapp.presentation.eventstate.notes.NotesState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(
    private val fetchAllNotesUseCase: FetchAllNotesUseCase,
    private val deleteNoteUseCase: DeleteNoteUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow<NotesState>(NotesState.LoadingState)
    val uiState: StateFlow<NotesState> = _uiState.asStateFlow()

    init {
        obtainEvent(NotesEvent.LoadNotes)
    }

    fun obtainEvent(event: NotesEvent) {
        when (event) {
            NotesEvent.LoadNotes -> {
                _uiState.value = NotesState.LoadingState
                viewModelScope.launch(Dispatchers.IO) {
                    try {
                        val notes = fetchAllNotesUseCase.execute()
                        val cmp = compareBy<NoteEntity> {
                            LocalDateTime.parse(
                                it.editTimestamp,
                                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                            )
                        }
                        _uiState.emit(NotesState.LoadedState(notes.sortedWith(cmp).reversed()))
                    } catch (e: Exception) {
                        _uiState.emit(NotesState.ErrorState(e.localizedMessage.orEmpty()))
                    }
                }
            }

            is NotesEvent.DeleteNote -> {
                viewModelScope.launch(Dispatchers.IO) {
                    deleteNoteUseCase.execute(event.note)
                    obtainEvent(NotesEvent.LoadNotes)
                }
            }
        }
    }
}