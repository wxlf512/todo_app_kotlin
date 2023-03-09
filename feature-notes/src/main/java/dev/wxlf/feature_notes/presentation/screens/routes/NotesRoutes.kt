package dev.wxlf.feature_notes.presentation.screens.routes

sealed class NotesRoutes(val route: String) {
    object AddNote : NotesRoutes("notes/add")
    object EditNote : NotesRoutes("notes/edit")
}