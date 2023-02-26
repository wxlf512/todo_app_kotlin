package dev.wxlf.todoapp.presentation.screens.routes

sealed class NotesRoutes(val route: String) {
    object AddNote : NotesRoutes("notes/add")
    object EditNote : NotesRoutes("notes/edit")
}