package dev.wxlf.todoapp.presentation.eventstate.notes

import dev.wxlf.todoapp.data.entities.NoteEntity

sealed class NotesEvent {
    object LoadNotes : NotesEvent()
    data class DeleteNote(val note: NoteEntity) : NotesEvent()
}
