package com.derra.notesapp.data

import androidx.compose.ui.graphics.Color
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class NoteRepositoryImpl (
    private val dao: NoteDao
        ) : NoteRepository
{
    override suspend fun insertNote(notecolor: NoteColor) {

       dao.insertNote(toNote(notecolor))
    }

    override suspend fun deleteNote(notecolor: NoteColor) {
        dao.deleteNote(toNote(notecolor))
    }

    override suspend fun getNoteById(id: Int): NoteColor? {
        return toNoteColor(dao.getNoteById(id))
    }

    override fun getNotes(): Flow<List<NoteColor>> {
        val noteList = dao.getNotes()
        return convertNotesToNoteColors(noteList)
    }

    override fun getNotesByTextAsc(): Flow<List<NoteColor>> {
        val noteList = dao.getNotesByTextAsc()
        return convertNotesToNoteColors(noteList)
    }

    override fun getNotesByTextDec(): Flow<List<NoteColor>> {
        val noteList = dao.getNotesByTextDec()
        return convertNotesToNoteColors(noteList)
    }

    override fun getNotesByColorAsc(): Flow<List<NoteColor>> {
        val noteList = dao.getNotesByColorAsc()
        return convertNotesToNoteColors(noteList)
    }

    override fun getNotesByColorDec(): Flow<List<NoteColor>> {
        val noteList = dao.getNotesByColorDec()
        return convertNotesToNoteColors(noteList)
    }


    private fun convertNotesToNoteColors(noteList: Flow<List<Note>>): Flow<List<NoteColor>> {
        return noteList.map { notes ->
            notes.mapNotNull { note ->
                toNoteColor(note)
            }
        }
    }
    private fun toNote(noteColor: NoteColor) : Note {
        return when (noteColor.color) {
            Color.Green -> {
                return Note(noteColor.title, noteColor.description, noteColor.id, "green")
            }
            Color.Red -> {
                return Note(noteColor.title, noteColor.description, noteColor.id, "red")
            }
            Color.Magenta -> {
                return Note(noteColor.title, noteColor.description, noteColor.id, "magenta")
            }
            Color.Yellow -> {
                return Note(noteColor.title, noteColor.description, noteColor.id, "yellow")
            }
            Color.Cyan -> {
                return Note(noteColor.title, noteColor.description, noteColor.id, "cyan")
            }
            else -> {return Note(noteColor.title, noteColor.description, noteColor.id, "red")}
        }
    }
    private fun toNoteColor(note: Note?) : NoteColor? {
        when (note?.color) {
            "green" -> {
                return NoteColor(note.title,note.description,note.id, Color.Green)
            }
            "magenta" -> {
                return NoteColor(note.title,note.description,note.id, Color.Magenta)
            }
            "yellow" -> {
                return NoteColor(note.title,note.description,note.id, Color.Yellow)
            }
            "cyan" -> {
                return NoteColor(note.title,note.description,note.id, Color.Cyan)
            }
            "red" -> {
                return NoteColor(note.title,note.description,note.id, Color.Red)
            }
            else -> return null
        }
    }

}


