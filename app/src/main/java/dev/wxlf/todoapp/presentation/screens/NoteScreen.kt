package dev.wxlf.todoapp.presentation.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import dev.wxlf.todoapp.presentation.eventstate.note.NoteEvent
import dev.wxlf.todoapp.presentation.eventstate.note.NoteState
import dev.wxlf.todoapp.presentation.viewmodels.NoteViewModel

@Composable
fun NoteScreen(viewModel: NoteViewModel, navController: NavHostController, id: Long? = null) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(key1 = Unit, block = {
        if (id != null)
            viewModel.obtainEvent(NoteEvent.LoadNote(id))
    })

    when (uiState) {
        NoteState.DeletedState -> navController.navigateUp()
        NoteState.EmptyState -> {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                Text(text = "Empty")
                Button(onClick = {
                    viewModel.obtainEvent(
                        NoteEvent.SaveNote(
                            noteName = "Lorem ipsum dolor sit amet aaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
                            noteData = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam in aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaadadad"
                        )
                    )
                }) {
                    Text("Save")
                }
            }
        }

        is NoteState.ErrorState -> {
            Box(modifier = Modifier.fillMaxSize()) {
                Text(
                    (uiState as NoteState.ErrorState).msg,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }

        is NoteState.LoadedState -> {
            val note = (uiState as NoteState.LoadedState).note
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                Text(text = (uiState as NoteState.LoadedState).note.toString())
                Button(onClick = { viewModel.obtainEvent(NoteEvent.DeleteNote(note)) }) {
                    Text("Delete")
                }
                Button(onClick = {
                    viewModel.obtainEvent(
                        NoteEvent.SaveNote(
                            noteName = note.noteName + "1",
                            noteData = note.noteData + "1"
                        )
                    )
                }) {
                    Text("Save")
                }
            }
        }

        NoteState.LoadingState -> {
            Box(modifier = Modifier.fillMaxSize()) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        }
    }
}