package com.derra.notesapp.ui.note_list

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.derra.notesapp.data.NoteColor
import com.derra.notesapp.data.NoteRepository
import com.derra.notesapp.util.Routes
import com.derra.notesapp.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteListViewModel @Inject constructor(
    private val repository: NoteRepository
) : ViewModel() {

    private val _uiEvent = Channel<UiEvent>()

    val uiEvent = _uiEvent.receiveAsFlow()
    var notes by mutableStateOf(repository.getNotesByTextAsc())
        private set
    var visibilityRadioButtons by mutableStateOf(false)
        private set
    var titleSelected by mutableStateOf(true)
        private set
    var colorSelected by mutableStateOf(false)
        private set
    var ascSelected by mutableStateOf(true)
        private set
    var descSelected by mutableStateOf(false)
        private set

    private var deletedNote: NoteColor? = null

    fun onEvent(event: NoteListEvent) {
        when (event) {
            is NoteListEvent.OnNoteClick -> {
                sendUiEvent(UiEvent.Navigate(Routes.ADD_EDIT_NOTE + "?noteId=${event.note.id}"))
            }
            is NoteListEvent.OnAddNoteClick -> {
                sendUiEvent(UiEvent.Navigate(Routes.ADD_EDIT_NOTE))
            }
            is NoteListEvent.OnUndoDeleteCLick -> {
                deletedNote?.let { note ->
                    viewModelScope.launch {
                        repository.insertNote(note)
                    }

                }
            }
            is NoteListEvent.OnDeleteNoteClick -> {
                viewModelScope.launch {
                    deletedNote = event.note
                    repository.deleteNote(event.note)
                    sendUiEvent(UiEvent.ShowSnackbar(
                        "note deleted",
                        "Undo"
                    ))
                }
            }
            is NoteListEvent.TextRadioButtonClick -> {
                colorSelected = false
                titleSelected = true
                notes =  if (ascSelected) repository.getNotesByTextAsc() else repository.getNotesByTextDec()
            }
            is NoteListEvent.ColorRadioButtonClick -> {
                colorSelected = true
                titleSelected = false

                notes = if (ascSelected) repository.getNotesByColorAsc() else repository.getNotesByColorDec()
            }
            is NoteListEvent.AscRadioButtonClick -> {
                ascSelected = true
                descSelected = false
                notes = if (colorSelected) repository.getNotesByColorAsc() else repository.getNotesByTextAsc()
            }
            is NoteListEvent.DescRadioButtonClick-> {
                ascSelected = false
                descSelected = true
                notes = if (colorSelected) repository.getNotesByColorDec() else repository.getNotesByTextDec()
            }
            is NoteListEvent.OrderButtonClick -> {
                visibilityRadioButtons = !visibilityRadioButtons
            }


        }

    }

    private fun sendUiEvent(event: UiEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }
}