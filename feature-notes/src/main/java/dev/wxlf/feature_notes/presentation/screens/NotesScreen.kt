package dev.wxlf.feature_notes.presentation.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import dev.wxlf.feature_notes.presentation.elements.NoteElement
import dev.wxlf.feature_notes.presentation.eventstate.notes.NotesEvent
import dev.wxlf.feature_notes.presentation.eventstate.notes.NotesState
import dev.wxlf.feature_notes.presentation.screens.routes.NotesRoutes
import dev.wxlf.feature_notes.presentation.viewmodels.NotesViewModel

@Composable
fun NotesScreen(viewModel: NotesViewModel, navController: NavHostController, paddingValues: PaddingValues) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(key1 = Unit, block = {
        viewModel.obtainEvent(NotesEvent.LoadNotes)
    })

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
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.align(
                            Alignment.Center
                        )
                    ) {
                        Text(
                            text = "You don't have any notes right now",
                            textAlign = TextAlign.Center
                        )
                        Button(onClick = { navController.navigate(NotesRoutes.AddNote.route) }) {
                            Text("Add note")
                        }
                    }
                }
            } else {
                LazyColumn(modifier = Modifier.padding(paddingValues).fillMaxSize().padding(vertical = 4.dp)) {
                    notes.forEach {
                        item {
                            NoteElement(
                                note = it,
                                delete = { viewModel.obtainEvent(NotesEvent.DeleteNote(it)) },
                                modifier = Modifier
                                    .padding(horizontal = 16.dp, vertical = 8.dp)
                                    .fillMaxWidth()
                                    .height(132.dp)
                                    .clickable { navController.navigate(NotesRoutes.EditNote.route + "/${it.id}") })
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