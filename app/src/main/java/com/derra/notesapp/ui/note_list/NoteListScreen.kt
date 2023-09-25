package com.derra.notesapp.ui.note_list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.derra.notesapp.util.UiEvent

import kotlinx.coroutines.flow.collect


@Composable
fun NoteListScreen(
    onNavigate: (UiEvent.Navigate) -> Unit,
    viewModel: NoteListViewModel = hiltViewModel()
){
    val darkGray = Color(0xFF202020)
    val notes = viewModel.notes.collectAsState(initial = emptyList())
    val scaffoldState = rememberScaffoldState()
    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.ShowSnackbar -> {
                    val result = scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message,
                        actionLabel = event.action
                    )
                    if (result == SnackbarResult.ActionPerformed) {
                        viewModel.onEvent(NoteListEvent.OnUndoDeleteCLick)
                    }
                }
                is UiEvent.Navigate -> onNavigate(event)
                else -> Unit
            }

        }
    }
    Scaffold  (
        scaffoldState = scaffoldState,
        backgroundColor = darkGray,
        modifier = Modifier.fillMaxSize(),
        floatingActionButton = {
            FloatingActionButton(onClick = {
                viewModel.onEvent(NoteListEvent.OnAddNoteClick)
            }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add")
            }
        }
            ){
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)) {
            Row (
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    modifier = Modifier.padding(8.dp),
                    text = "Your notes",
                    style = androidx.compose.ui.text.TextStyle(color = Color.White,fontSize = 30.sp)
                )
                IconButton(onClick = {
                    viewModel.onEvent(NoteListEvent.OrderButtonClick)

                }) {
                    Icon(imageVector = Icons.Default.Menu, contentDescription = "Menu")
                }


            }
            if (viewModel.visibilityRadioButtons) {
                Column(
                ) {
                    Row() {
                        RadioButtonText(
                            selected = viewModel.titleSelected,
                            onEvent1 = {viewModel.onEvent(NoteListEvent.TextRadioButtonClick)},
                            text = "Title",
                            onEvent2 = {viewModel.onEvent(NoteListEvent.ColorRadioButtonClick)},
                            text2 = "Color"
                        )
                    }
                    Row() {
                        RadioButtonText(
                            selected = viewModel.ascSelected,
                            onEvent1 = { viewModel.onEvent(NoteListEvent.AscRadioButtonClick) },
                            text = "Ascending",
                            onEvent2 = { viewModel.onEvent(NoteListEvent.DescRadioButtonClick)},
                            text2 = "Descending"
                        )

                    }
                }
            }


            Spacer(modifier = Modifier.height(8.dp))

            LazyColumn(modifier = Modifier
                .fillMaxHeight(0.8f)
                .fillMaxWidth()
                .padding(8.dp)) {
                items(notes.value) {note->
                    NoteItem(note = note, onEvent = viewModel::onEvent, modifier = Modifier.clickable { viewModel.onEvent(NoteListEvent.OnNoteClick(note)) })
                    Spacer(modifier = Modifier.height(20.dp))
                }
            }
        }


    }

}