package com.derra.notesapp.ui.add_edit_note

import androidx.compose.ui.graphics.Color

sealed class AddEditNoteEvent {
    data class OnTitleChange(val title: String): AddEditNoteEvent()
    data class OnDescriptionChange(val description: String): AddEditNoteEvent()
    data class OnColorChange(val color: Color): AddEditNoteEvent()
    object OnSaveNoteClick: AddEditNoteEvent()

}
