package dev.wxlf.feature_notes.presentation.eventstate.notes

import dev.wxlf.feature_notes.data.entities.NoteEntity

sealed class NotesState {
    object LoadingState : NotesState()
    data class LoadedState(val notes: List<NoteEntity>) : NotesState()
    data class ErrorState(val msg: String) : NotesState()
}
