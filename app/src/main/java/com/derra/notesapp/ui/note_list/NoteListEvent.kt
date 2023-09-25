package com.derra.notesapp.ui.note_list


import com.derra.notesapp.data.NoteColor

sealed class NoteListEvent {
    data class OnDeleteNoteClick(val note: NoteColor): NoteListEvent()
    object OnUndoDeleteCLick: NoteListEvent()
    data class OnNoteClick(val note: NoteColor): NoteListEvent()
    object OnAddNoteClick: NoteListEvent()
    object OrderButtonClick: NoteListEvent()
    object TextRadioButtonClick: NoteListEvent()
    object ColorRadioButtonClick: NoteListEvent()
    object AscRadioButtonClick: NoteListEvent()
    object DescRadioButtonClick: NoteListEvent()
}
