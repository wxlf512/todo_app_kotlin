package dev.wxlf.todoapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint
import dev.wxlf.todoapp.domain.usecases.notes.AddNoteUseCase
import dev.wxlf.todoapp.presentation.screens.NoteScreen
import dev.wxlf.todoapp.presentation.screens.NotesScreen
import dev.wxlf.todoapp.presentation.screens.routes.MainRoutes
import dev.wxlf.todoapp.presentation.screens.routes.NotesRoutes
import dev.wxlf.todoapp.presentation.theme.TodoAppTheme
import dev.wxlf.todoapp.presentation.viewmodels.NoteViewModel
import dev.wxlf.todoapp.presentation.viewmodels.NotesViewModel
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
                val bottomDestinations = listOf(MainRoutes.Notes, MainRoutes.TODO)

                val systemUiController = rememberSystemUiController()
                val systemBarColor = Color.Transparent
                val isDarkMode = isSystemInDarkTheme()
                SideEffect {
                    systemUiController.setNavigationBarColor(
                        color = systemBarColor,
                        darkIcons = !isDarkMode
                    )
                }

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
                                            MainRoutes.Notes.route -> Text(MainRoutes.Notes.label)
                                            MainRoutes.TODO.route -> Text(MainRoutes.TODO.label)
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
                                MainRoutes.Notes.route -> {
                                    FloatingActionButton(
                                        onClick = {
                                            navController.navigate(NotesRoutes.AddNote.route)
                                        },
                                    ) {
                                        Icon(Icons.Default.Add, contentDescription = "Add note")
                                    }
                                }
                            }
                        }
                    ) { paddingValues ->
                        NavHost(
                            navController = navController,
                            startDestination = MainRoutes.Notes.route
                        ) {
                            composable(MainRoutes.Notes.route) {
                                val notesViewModel = hiltViewModel<NotesViewModel>()
                                NotesScreen(
                                    viewModel = notesViewModel,
                                    navController = navController,
                                    paddingValues = paddingValues
                                )
                            }
                            composable(MainRoutes.TODO.route) {
                                Box(modifier = Modifier.fillMaxSize()) {
                                    Text(
                                        "TODO", fontSize = 32.sp, modifier = Modifier
                                            .align(Alignment.Center)
                                    )
                                }
                            }
                            composable(NotesRoutes.AddNote.route) {
                                val noteViewModel = hiltViewModel<NoteViewModel>()
                                NoteScreen(viewModel = noteViewModel, navController = navController)
                            }
                            composable(NotesRoutes.EditNote.route + "/{id}",
                                arguments = listOf(navArgument("id") { type = NavType.LongType })) { backStackEntry ->
                                val noteViewModel = hiltViewModel<NoteViewModel>()
                                NoteScreen(
                                    viewModel = noteViewModel,
                                    navController = navController,
                                    id = backStackEntry.arguments?.getLong("id")
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
