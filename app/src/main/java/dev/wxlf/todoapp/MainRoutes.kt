package dev.wxlf.todoapp

import androidx.annotation.DrawableRes

sealed class MainRoutes(val route: String, val label: String, @DrawableRes val icon: Int) {
    object Notes : MainRoutes("notes", "Notes", R.drawable.baseline_notes_24)
    object TaskTable : MainRoutes("task_table", "Task table", R.drawable.baseline_table_chart_24)
}