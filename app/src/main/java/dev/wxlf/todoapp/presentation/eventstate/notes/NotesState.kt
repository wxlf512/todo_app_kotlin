package dev.wxlf.todoapp.presentation.eventstate.notes

import dev.wxlf.todoapp.data.entities.NoteEntity

sealed class NotesState{
    object LoadingState : NotesState()
    data class LoadedState(val notes: List<NoteEntity>) : NotesState()
    data class ErrorState(val msg: String) : NotesState()
}
