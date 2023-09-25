package com.derra.notesapp.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.derra.notesapp.ui.add_edit_note.AddEditNoteScreen
import com.derra.notesapp.ui.note_list.NoteListScreen
import com.derra.notesapp.ui.theme.NotesAppTheme
import com.derra.notesapp.util.Routes
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NotesAppTheme {
                androidx.compose.material.Surface(
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = Routes.NOTE_LIST) {
                        composable(Routes.NOTE_LIST) {
                            NoteListScreen(onNavigate = {
                                navController.navigate(it.route)
                            })
                        }
                        composable(
                            route = Routes.ADD_EDIT_NOTE + "?noteId={noteId}",
                            arguments = listOf(
                                navArgument(name = "noteId") {
                                    type = NavType.IntType
                                    defaultValue = -1
                                }
                            )
                        ) {
                            AddEditNoteScreen(onPopBackStack = { navController.popBackStack()})
                        }
                    }

                }
            }
        }
    }
}

