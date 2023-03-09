package dev.wxlf.feature_notes.presentation.eventstate.note

import dev.wxlf.feature_notes.data.entities.NoteEntity

sealed class NoteEvent {
    data class LoadNote(val id: Long) : NoteEvent()
    data class SaveNote(val noteName: String, val noteData: String) : NoteEvent()
    data class DeleteNote(val note: NoteEntity) : NoteEvent()
}
