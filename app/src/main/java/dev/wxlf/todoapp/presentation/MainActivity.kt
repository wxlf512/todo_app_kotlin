package dev.wxlf.todoapp.presentation

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import dev.wxlf.todoapp.domain.usecases.notes.AddNoteUseCase
import dev.wxlf.todoapp.presentation.screens.NotesScreen
import dev.wxlf.todoapp.presentation.screens.Routes
import dev.wxlf.todoapp.presentation.theme.TodoAppTheme
import dev.wxlf.todoapp.presentation.viewmodels.NotesViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var addNoteUseCase: AddNoteUseCase

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TodoAppTheme {
                val navController = rememberNavController()
                val currentRoute =
                    navController.currentBackStackEntryFlow.collectAsState(initial = navController.currentBackStackEntry)
                val bottomDestinations = listOf(Routes.Notes, Routes.TODO)
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Scaffold(
                        topBar = {
                            if (bottomDestinations.any { it.route == currentRoute.value?.destination?.route })
                                TopAppBar(
                                    title = {
                                        when (currentRoute.value?.destination?.route) {
                                            Routes.Notes.route -> Text(Routes.Notes.label)
                                            Routes.TODO.route -> Text(Routes.TODO.label)
                                        }
                                    },
                                    colors = TopAppBarDefaults.mediumTopAppBarColors(
                                        containerColor = MaterialTheme.colorScheme.primary,
                                        navigationIconContentColor = MaterialTheme.colorScheme.onPrimary,
                                        titleContentColor = MaterialTheme.colorScheme.onPrimary,
                                        actionIconContentColor = MaterialTheme.colorScheme.onPrimary
                                    )
                                )
                        },
                        bottomBar = {
                            if (bottomDestinations.any { it.route == currentRoute.value?.destination?.route })
                                NavigationBar {
                                    bottomDestinations.forEach {
                                        NavigationBarItem(
                                            selected = currentRoute.value?.destination?.route == it.route,
                                            onClick = { navController.navigate(it.route) },
                                            label = { Text(it.label) },
                                            icon = {
                                                Icon(
                                                    painterResource(id = it.icon),
                                                    contentDescription = it.label
                                                )
                                            })
                                    }
                                }
                        },
                        floatingActionButton = {
                            when (currentRoute.value?.destination?.route) {
                                Routes.Notes.route -> {
                                    FloatingActionButton(
                                        onClick = {
                                            Toast.makeText(
                                                this,
                                                "Add note",
                                                Toast.LENGTH_SHORT
                                            ).show()
                                            GlobalScope.launch(Dispatchers.IO) {
                                                addNoteUseCase.execute("Note", "Data")
                                            }
                                        },
                                    ) {
                                        Icon(Icons.Default.Add, contentDescription = "Add note")
                                    }
                                }
                            }
                        }
                    ) {
                        NavHost(
                            modifier = Modifier.padding(it),
                            navController = navController,
                            startDestination = Routes.Notes.route
                        ) {
                            composable(Routes.Notes.route) {
                                val notesViewModel = hiltViewModel<NotesViewModel>()
                                NotesScreen(
                                    viewModel = notesViewModel,
                                    navController = navController
                                )
                            }
                            composable(Routes.TODO.route) {
                                Box(modifier = Modifier.fillMaxSize()) {
                                    Text(
                                        "TODO", fontSize = 32.sp, modifier = Modifier
                                            .align(Alignment.Center)
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
