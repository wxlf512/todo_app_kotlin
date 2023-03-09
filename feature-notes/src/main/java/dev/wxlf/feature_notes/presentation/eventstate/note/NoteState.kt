package dev.wxlf.feature_notes.presentation.eventstate.note

import dev.wxlf.feature_notes.data.entities.NoteEntity

sealed class NoteState{
    object EmptyState : NoteState()
    object LoadingState : NoteState()
    object DeletedState : NoteState()
    data class LoadedState(val note: NoteEntity) : NoteState()
    data class ErrorState(val msg: String) : NoteState()
}
