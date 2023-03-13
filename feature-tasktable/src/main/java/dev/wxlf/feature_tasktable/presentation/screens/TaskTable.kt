package dev.wxlf.feature_tasktable.presentation.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp

@Composable
fun TaskTable(paddingValues: PaddingValues) {
    Box(modifier = Modifier
        .padding(paddingValues)
        .fillMaxSize()) {
        Text(
            "Task table", fontSize = 32.sp, modifier = Modifier
                .align(Alignment.Center)
        )
    }
}