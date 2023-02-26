package dev.wxlf.todoapp.presentation.eventstate.note

import dev.wxlf.todoapp.data.entities.NoteEntity

sealed class NoteEvent {
    data class LoadNote(val id: Long) : NoteEvent()
    data class SaveNote(val noteName: String, val noteData: String) : NoteEvent()
    data class DeleteNote(val note: NoteEntity) : NoteEvent()
}
