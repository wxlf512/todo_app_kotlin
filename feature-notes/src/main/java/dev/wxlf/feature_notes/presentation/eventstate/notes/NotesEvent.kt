package dev.wxlf.feature_notes.presentation.eventstate.notes

import dev.wxlf.feature_notes.data.entities.NoteEntity

sealed class NotesEvent {
    object LoadNotes : NotesEvent()
    data class DeleteNote(val note: NoteEntity) : NotesEvent()
}
