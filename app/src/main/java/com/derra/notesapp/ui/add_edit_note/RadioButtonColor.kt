package com.derra.notesapp.ui.add_edit_note


import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.RadioButton
import androidx.compose.material.RadioButtonColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp
import com.derra.notesapp.ui.note_list.NoteListEvent

@Composable
fun RadioButtonColor(
    onEvent: (AddEditNoteEvent) -> Unit,
    color: Color

){
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {

        Box(
            modifier = Modifier
                .size(55.dp)
                .border(
                    width = 3.dp,
                    color = if (color == Color.Red) Color.Black else Color.Transparent,
                    shape = CircleShape
                )
                .background(color = Color.Red, shape = CircleShape)
                .clickable {
                    onEvent(AddEditNoteEvent.OnColorChange(Color.Red))
                }
        )

        Box(
            modifier = Modifier
                .size(55.dp)
                .border(
                    width = 3.dp,
                    color = if (color == Color.Yellow) Color.Black else Color.Transparent,
                    shape = CircleShape
                )
                .background(color = Color.Yellow, shape = CircleShape)
                .clickable {
                    onEvent(AddEditNoteEvent.OnColorChange(Color.Yellow))
                }
        )

        Box(
            modifier = Modifier
                .size(55.dp)
                .border(
                    width = 3.dp,
                    color = if (color == Color.Magenta) Color.Black else Color.Transparent,
                    shape = CircleShape
                )
                .background(color = Color.Magenta, shape = CircleShape)
                .clickable {
                    onEvent(AddEditNoteEvent.OnColorChange(Color.Magenta))
                }
        )

        Box(
            modifier = Modifier
                .size(55.dp)
                .border(
                    width = 3.dp,
                    color = if (color == Color.Cyan) Color.Black else Color.Transparent,
                    shape = CircleShape
                )
                .background(color = Color.Cyan, shape = CircleShape)
                .clickable {
                    onEvent(AddEditNoteEvent.OnColorChange(Color.Cyan))
                }
        )

        Box(
            modifier = Modifier
                .size(55.dp)
                .border(
                    width = 3.dp,
                    color = if (color == Color.Green) Color.Black else Color.Transparent,
                    shape = CircleShape
                )
                .background(color = Color.Green, shape = CircleShape)
                .clickable {
                    onEvent(AddEditNoteEvent.OnColorChange(Color.Green))
                }
        )


    }

}