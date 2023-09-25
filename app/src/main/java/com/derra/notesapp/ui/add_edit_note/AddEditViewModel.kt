package com.derra.notesapp.ui.add_edit_note

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.derra.notesapp.data.Note
import com.derra.notesapp.data.NoteColor
import com.derra.notesapp.data.NoteRepository
import com.derra.notesapp.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditViewModel @Inject constructor(
    private val repository: NoteRepository,
    savedStateHandle: SavedStateHandle
)  : ViewModel(){
    var note by mutableStateOf<NoteColor?>(null)
        private set
    var title by mutableStateOf("")
        private set
    var description by mutableStateOf("")
        private set
    var color by mutableStateOf(Color.Red)
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init {
        val noteId = savedStateHandle.get<Int>("noteId")!!
        if (noteId != -1) {
            viewModelScope.launch {
                repository.getNoteById(noteId)?.let {note ->
                    title = note.title
                    description = note.description ?: ""
                    val hexColor = Integer.toHexString(note.color.toArgb())
                    color = note.color
                    this@AddEditViewModel.note = note
                }
            }
        }
    }

    fun onEvent(event: AddEditNoteEvent) {
        when(event) {
            is AddEditNoteEvent.OnTitleChange -> {
                title = event.title
            }
            is AddEditNoteEvent.OnDescriptionChange -> {
                description = event.description
            }
            is AddEditNoteEvent.OnColorChange -> {
                color = event.color
            }
            is AddEditNoteEvent.OnSaveNoteClick -> {
                viewModelScope.launch {
                    if(title.isBlank()) {
                        sendUiEvent(UiEvent.ShowSnackbar(
                            message = "the title can't be empty"
                        ))
                        return@launch
                    }
                    repository.insertNote(
                        NoteColor(
                        title = title,
                        description = description,
                        color = color,
                        id = note?.id
                        )
                    )
                    sendUiEvent(UiEvent.PopBackStack)
                }

            }
        }

    }
    private fun sendUiEvent(event: UiEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }



}