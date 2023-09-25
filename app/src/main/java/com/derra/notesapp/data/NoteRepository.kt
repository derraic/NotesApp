package com.derra.notesapp.data

import kotlinx.coroutines.flow.Flow

interface NoteRepository {
    suspend fun insertNote(noteColor: NoteColor)

    suspend fun deleteNote(noteColor: NoteColor)

    suspend fun getNoteById(id: Int): NoteColor?

    fun getNotes(): Flow<List<NoteColor>>


    fun getNotesByTextAsc(): Flow<List<NoteColor>>

    fun getNotesByTextDec(): Flow<List<NoteColor>>

    fun getNotesByColorAsc(): Flow<List<NoteColor>>

    fun getNotesByColorDec(): Flow<List<NoteColor>>
}