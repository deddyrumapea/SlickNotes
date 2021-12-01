package com.romnan.slicknotes.feature_note.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.romnan.slicknotes.feature_note.presentation.add_edit_note.AddEditNoteScreen
import com.romnan.slicknotes.feature_note.presentation.notes.NotesScreen
import com.romnan.slicknotes.feature_note.presentation.util.Screen
import com.romnan.slicknotes.ui.theme.SlickNotesTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @ExperimentalAnimationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SlickNotesTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.Notes.route
                    ) {
                        composable(route = Screen.Notes.route) {
                            NotesScreen(navController = navController)
                        }
                        composable(
                            route = Screen.AddEditNote.route +
                                    "?${Screen.AddEditNote.ARG_NOTE_ID}" +
                                    "={${Screen.AddEditNote.ARG_NOTE_ID}}" +
                                    "&${Screen.AddEditNote.ARG_NOTE_COLOR}" +
                                    "={${Screen.AddEditNote.ARG_NOTE_COLOR}}",
                            arguments = listOf(
                                navArgument(Screen.AddEditNote.ARG_NOTE_ID) {
                                    type = NavType.IntType
                                    defaultValue = -1
                                },
                                navArgument(Screen.AddEditNote.ARG_NOTE_COLOR) {
                                    type = NavType.IntType
                                    defaultValue = -1
                                }
                            )
                        ) {
                            val color =
                                it.arguments?.getInt(Screen.AddEditNote.ARG_NOTE_COLOR) ?: -1
                            AddEditNoteScreen(navController = navController, noteColor = color)
                        }
                    }
                }
            }
        }
    }
}