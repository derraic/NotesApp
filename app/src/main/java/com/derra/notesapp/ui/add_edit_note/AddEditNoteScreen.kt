package com.derra.notesapp.ui.add_edit_note

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.derra.notesapp.util.UiEvent
import kotlinx.coroutines.flow.collect

@Composable

fun AddEditNoteScreen (
    onPopBackStack: () -> Unit,
    viewModel: AddEditViewModel = hiltViewModel()
) {
    val textFieldColors = TextFieldDefaults.textFieldColors(
        backgroundColor = Color.Transparent, // Set the background color to transparent
        focusedIndicatorColor = Color.Transparent, // Set the focused indicator color to transparent
        unfocusedIndicatorColor = Color.Transparent // Set the unfocused indicator color to transparent
    )
    val scaffoldState = rememberScaffoldState()
    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when(event) {
                is UiEvent.PopBackStack -> onPopBackStack()
                is UiEvent.ShowSnackbar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message,
                        actionLabel = event.action
                    )
                }
                else -> Unit
            }

        }

    }

    Scaffold(
        scaffoldState = scaffoldState,
        modifier = Modifier
            .fillMaxSize(),
        floatingActionButton = {
            FloatingActionButton(onClick = { viewModel.onEvent(AddEditNoteEvent.OnSaveNoteClick)}) {
                Icon(imageVector = Icons.Default.Check, contentDescription = "Save")
            }
        }
    ) {
        Column(modifier = Modifier.fillMaxSize().background(color = viewModel.color).padding(16.dp)) {
            RadioButtonColor(onEvent = viewModel::onEvent, viewModel.color)
            Spacer(modifier = Modifier.height(14.dp))
            TextField(
                value = viewModel.title,
                onValueChange = {
                    viewModel.onEvent(AddEditNoteEvent.OnTitleChange(it))
                },
                textStyle = TextStyle(
                    fontSize = 26.sp,
                    fontWeight = FontWeight.SemiBold,
                ),

                placeholder = {
                    Text(
                        text = "Choose a title",
                        fontSize = 26.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                },
                shape = RectangleShape,
                colors = textFieldColors ,
                modifier = Modifier.fillMaxWidth()
            )
            TextField(
                value = viewModel.description,
                onValueChange = {
                    viewModel.onEvent(AddEditNoteEvent.OnDescriptionChange(it))
                },
                textStyle = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium,
                ),
                placeholder = {
                    Text(
                        text = "Enter some content...",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Medium
                    )
                },
                shape = RectangleShape,
                colors = textFieldColors,
                modifier = Modifier.fillMaxWidth(),
                singleLine = false,
                maxLines = 5
            )
        }
    }
}