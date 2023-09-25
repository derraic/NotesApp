package com.derra.notesapp.data


import kotlinx.coroutines.flow.Flow
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface NoteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: Note)

    @Delete
    suspend fun deleteNote(note: Note)

    @Query("SELECT * FROM note where id = :id")
    suspend fun getNoteById(id: Int): Note?

    @Query("SELECT * FROM note")
    fun getNotes(): Flow<List<Note>>

    @Query("SELECT * FROM note ORDER BY title ASC")
    fun getNotesByTextAsc(): Flow<List<Note>>

    @Query("SELECT * FROM note ORDER BY title DESC")
    fun getNotesByTextDec(): Flow<List<Note>>

    @Query("SELECT * FROM note ORDER BY color ASC")
    fun getNotesByColorAsc(): Flow<List<Note>>

    @Query("SELECT * FROM note ORDER BY color DESC")
    fun getNotesByColorDec(): Flow<List<Note>>

}