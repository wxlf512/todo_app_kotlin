package dev.wxlf.todoapp.presentation.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import dev.wxlf.todoapp.R
import dev.wxlf.todoapp.presentation.elements.TransparentBackgroundTextField
import dev.wxlf.todoapp.presentation.eventstate.note.NoteEvent
import dev.wxlf.todoapp.presentation.eventstate.note.NoteState
import dev.wxlf.todoapp.presentation.viewmodels.NoteViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteScreen(viewModel: NoteViewModel, navController: NavHostController, id: Long? = null) {
    val uiState by viewModel.uiState.collectAsState()
    var noteName by remember { mutableStateOf("") }
    var noteData by remember { mutableStateOf("") }
    var oldNoteName by remember { mutableStateOf("") }
    var oldNoteData by remember { mutableStateOf("") }

    BackHandler {
        if (noteName != "" || noteData != "")
            viewModel.obtainEvent(NoteEvent.SaveNote(noteName, noteData))
        navController.navigateUp()
    }

    LaunchedEffect(key1 = Unit, block = {
        if (id != null)
            viewModel.obtainEvent(NoteEvent.LoadNote(id))
    })

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    TransparentBackgroundTextField(
                        value = noteName,
                        placeholder = "Note name",
                        onValueChange = { name -> noteName = name },
                        singleLine = true,
                        textColor = MaterialTheme.colorScheme.onPrimary,
                        cursorColor = MaterialTheme.colorScheme.onPrimary,
                        placeholderTextStyle = TextStyle(color = MaterialTheme.colorScheme.onTertiary, fontWeight = FontWeight.Normal, fontSize = 24.sp)
                    )
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    actionIconContentColor = MaterialTheme.colorScheme.onPrimary
                ),
                navigationIcon = {
                    IconButton(onClick = {
                        if (noteName != "" || noteData != "")
                            viewModel.obtainEvent(NoteEvent.SaveNote(noteName, noteData))
                        navController.navigateUp()
                    }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Save & Exit")
                    }
                },
                actions = {
                    if (id != null && (noteName != oldNoteName || noteData != oldNoteData))
                        IconButton(onClick = {
                            noteName = oldNoteName
                            noteData = oldNoteData
                        }) {
                            Icon(
                                painterResource(R.drawable.baseline_undo_24),
                                contentDescription = "Undo"
                            )
                        }
                }
            )
        }
    ) {
        when (uiState) {
            NoteState.DeletedState -> navController.navigateUp()
            NoteState.EmptyState -> {
                LaunchedEffect(key1 = Unit, block = {
                    noteName = ""
                    noteData = ""
                })
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
                LaunchedEffect(key1 = Unit, block = {
                    val note = (uiState as NoteState.LoadedState).note
                    oldNoteName = note.noteName
                    noteName = note.noteName
                    oldNoteData = note.noteData
                    noteData = note.noteData
                })
            }

            NoteState.LoadingState -> {
                Box(modifier = Modifier.fillMaxSize()) {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
            }
        }
        if (uiState !is NoteState.ErrorState || uiState !is NoteState.LoadingState)
            TransparentBackgroundTextField(
                value = noteData,
                placeholder = "Note data",
                onValueChange = { data -> noteData = data },
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
            )
    }
}