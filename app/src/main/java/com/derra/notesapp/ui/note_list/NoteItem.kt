package com.derra.notesapp.ui.note_list


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.derra.notesapp.data.Note
import com.derra.notesapp.data.NoteColor


@Composable
fun NoteItem(
    note: NoteColor,
    onEvent: (NoteListEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    Box (
         modifier = modifier
             .background(color = note.color, shape = RoundedCornerShape(8.dp))
             .padding(6.dp)
             .fillMaxWidth(),
        contentAlignment = Alignment.Center,


            ){
        Column(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = note.title,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp

            )
            Spacer(modifier = Modifier.height(3.dp))

            Text(
                text = note.description ?: "",
                fontSize = 12.sp,
                overflow = TextOverflow.Ellipsis
            )
            Row(
                modifier = Modifier.align(Alignment.End)
            ) {
                Spacer(modifier = Modifier.width(3.dp))
                IconButton(onClick = {
                    onEvent(NoteListEvent.OnDeleteNoteClick(note))
                }) {
                    Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete")
                }
                }
        }

    }

}