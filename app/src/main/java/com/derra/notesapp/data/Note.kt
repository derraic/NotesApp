package com.derra.notesapp.data


import androidx.compose.ui.graphics.Color
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note")
data class Note(
    val title: String,
    val description: String?,
    @PrimaryKey val id: Int? = null,
    val color: String
)


