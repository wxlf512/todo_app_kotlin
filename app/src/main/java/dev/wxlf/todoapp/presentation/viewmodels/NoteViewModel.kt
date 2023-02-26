package dev.wxlf.todoapp.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.wxlf.todoapp.data.entities.NoteEntity
import dev.wxlf.todoapp.domain.usecases.notes.AddNoteUseCase
import dev.wxlf.todoapp.domain.usecases.notes.DeleteNoteUseCase
import dev.wxlf.todoapp.domain.usecases.notes.FetchNoteUseCase
import dev.wxlf.todoapp.domain.usecases.notes.UpdateNoteUseCase
import dev.wxlf.todoapp.presentation.eventstate.note.NoteEvent
import dev.wxlf.todoapp.presentation.eventstate.note.NoteState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(
    private val addNoteUseCase: AddNoteUseCase,
    private val fetchNoteUseCase: FetchNoteUseCase,
    private val updateNoteUseCase: UpdateNoteUseCase,
    private val deleteNoteUseCase: DeleteNoteUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow<NoteState>(NoteState.EmptyState)
    val uiState: StateFlow<NoteState> = _uiState.asStateFlow()

    private var note: NoteEntity? = null

    fun obtainEvent(event: NoteEvent) {
        when (event) {
            is NoteEvent.DeleteNote -> {
                viewModelScope.launch(Dispatchers.IO) {
                    deleteNoteUseCase.execute(event.note)
                    _uiState.emit(NoteState.DeletedState)
                }
            }

            is NoteEvent.LoadNote -> {
                viewModelScope.launch(Dispatchers.IO) {
                    try {
                        _uiState.emit(NoteState.LoadingState)
                        note = fetchNoteUseCase.execute(event.id)
                        _uiState.emit(NoteState.LoadedState(note!!))
                    } catch (e: Exception) {
                        _uiState.emit(NoteState.ErrorState(e.localizedMessage.orEmpty()))
                    }
                }
            }

            is NoteEvent.SaveNote -> {
                if (note == null) {
                    viewModelScope.launch(Dispatchers.IO) {
                        val id = addNoteUseCase.execute(event.noteName, event.noteData)
                        obtainEvent(NoteEvent.LoadNote(id))
                    }
                } else {
                    viewModelScope.launch(Dispatchers.IO) {
                        val current = LocalDateTime.now(ZoneOffset.UTC)
                        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                        val currentTimeStamp = current.format(formatter)
                        updateNoteUseCase.execute(
                            note!!.copy(
                                noteName = event.noteName,
                                noteData = event.noteData,
                                editTimestamp = currentTimeStamp
                            )
                        )
                        obtainEvent(NoteEvent.LoadNote(note!!.id))
                    }
                }
            }
        }
    }
}