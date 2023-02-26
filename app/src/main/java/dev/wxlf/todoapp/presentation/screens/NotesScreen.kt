package dev.wxlf.todoapp.presentation.screens

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import dev.wxlf.todoapp.presentation.eventstate.notes.NotesEvent
import dev.wxlf.todoapp.presentation.eventstate.notes.NotesState
import dev.wxlf.todoapp.presentation.viewmodels.NotesViewModel

@Composable
fun NotesScreen(viewModel: NotesViewModel, navController: NavHostController) {
    val uiState by viewModel.uiState.collectAsState()

    when (uiState) {
        is NotesState.ErrorState -> {
            Box(modifier = Modifier.fillMaxSize()) {
                Text(
                    text = (uiState as NotesState.ErrorState).msg,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
        is NotesState.LoadedState -> {
            val notes = (uiState as NotesState.LoadedState).notes
            if (notes.isEmpty()) {
                Box(modifier = Modifier.fillMaxSize()) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.align(
                        Alignment.Center)) {
                        val context = LocalContext.current
                        Text(text = "You don't have any notes right now", textAlign = TextAlign.Center)
                        Button(onClick = { Toast.makeText(context, "Add note", Toast.LENGTH_SHORT).show() }) {
                            Text("Add note")
                        }
                    }
                }
            } else {
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    notes.forEach {
                        item {
                            ElevatedCard(modifier = Modifier.padding(16.dp).clickable { viewModel.obtainEvent(NotesEvent.DeleteNote(it)) }) {
                                Text(text = it.toString(), modifier = Modifier.padding(8.dp))
                            }
                        }
                    }
                }
            }
        }
        NotesState.LoadingState -> {
            Box(modifier = Modifier.fillMaxSize()) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        }
    }
}