package dev.wxlf.todoapp.presentation.eventstate.note

import dev.wxlf.todoapp.data.entities.NoteEntity

sealed class NoteState{
    object EmptyState : NoteState()
    object LoadingState : NoteState()
    object DeletedState : NoteState()
    data class LoadedState(val note: NoteEntity) : NoteState()
    data class ErrorState(val msg: String) : NoteState()
}
