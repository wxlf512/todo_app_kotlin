package dev.wxlf.todoapp.presentation.screens

import androidx.annotation.DrawableRes
import dev.wxlf.todoapp.R

sealed class Routes(val route: String, val label: String, @DrawableRes val icon: Int) {
    object Notes : Routes("notes", "Notes", R.drawable.baseline_notes_24)
    object TODO : Routes("todo", "TODO", R.drawable.baseline_table_chart_24)
}