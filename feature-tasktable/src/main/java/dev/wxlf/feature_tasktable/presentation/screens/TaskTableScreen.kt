package dev.wxlf.feature_tasktable.presentation.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import dev.wxlf.feature_tasktable.presentation.eventstate.tasktable.TaskTableEvent
import dev.wxlf.feature_tasktable.presentation.eventstate.tasktable.TaskTableState
import dev.wxlf.feature_tasktable.presentation.viewmodels.TaskTableViewModel

@Composable
fun TaskTableScreen(
    viewModel: TaskTableViewModel,
    navController: NavHostController,
    paddingValues: PaddingValues
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(key1 = Unit, block = {
        viewModel.obtainEvent(TaskTableEvent.LoadTasks)
    })

    when (uiState) {
        is TaskTableState.ErrorState -> {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                Text(
                    text = (uiState as TaskTableState.ErrorState).msg,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }

        is TaskTableState.LoadedState -> {
            LazyRow(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                items((uiState as TaskTableState.LoadedState).columns) {
                    val tasks =
                        (uiState as TaskTableState.LoadedState).tasks.filter { task -> task.taskColumnId == it.id }
                    Card(
                        modifier = Modifier
                            .padding(8.dp)
                            .width(256.dp),
                        colors = CardDefaults.cardColors(containerColor = it.columnColor.toColor())
                    ) {
                        Text(
                            text = it.columnName,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(8.dp),
                            overflow = TextOverflow.Ellipsis
                        )
                        LazyColumn(
                            modifier = Modifier
                                .padding(8.dp)
                                .fillMaxWidth()
                        ) {
                            items(tasks) {
                                Card(modifier = Modifier.padding(vertical = 8.dp)) {
                                    Text(text = it.toString())
                                }
                            }
                            item {
                                Card(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clickable { viewModel.obtainEvent(TaskTableEvent.AddTask(it.id)) }) {
                                    Text(
                                        text = "Create new task",
                                        color = MaterialTheme.colorScheme.primary,
                                        modifier = Modifier
                                            .align(Alignment.CenterHorizontally)
                                            .padding(vertical = 8.dp)
                                    )
                                }
                            }
                        }
                    }
                }
                item {
                    Card(
                        modifier = Modifier
                            .padding(top = 8.dp, bottom = 8.dp, end = 8.dp)
                            .width(256.dp)
                            .clickable { viewModel.obtainEvent(TaskTableEvent.AddColumn) }
                    ) {
                        Text(
                            text = "Create new column",
                            color = MaterialTheme.colorScheme.primary,
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                                .padding(vertical = 8.dp)
                        )
                    }
                }
            }
        }

        TaskTableState.LoadingState -> {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        }
    }
}

fun String.toColor() = Color(android.graphics.Color.parseColor(this))