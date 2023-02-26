package dev.wxlf.todoapp.presentation.screens.routes

import androidx.annotation.DrawableRes
import dev.wxlf.todoapp.R

sealed class MainRoutes(val route: String, val label: String, @DrawableRes val icon: Int) {
    object Notes : MainRoutes("notes", "Notes", R.drawable.baseline_notes_24)
    object TODO : MainRoutes("todo", "TODO", R.drawable.baseline_table_chart_24)
}