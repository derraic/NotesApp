package com.derra.notesapp.ui.note_list


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun RadioButtonText(
    selected: Boolean,
    onEvent1: () -> Unit,
    text: String,
    onEvent2: () -> Unit,
    text2: String

) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            selected = selected,
            onClick = onEvent1
        )
        Text(
            text = text,
            style = TextStyle(color = Color.White, fontSize = 16.sp),
            modifier = Modifier.padding(start = 8.dp)
        )

        RadioButton(
            selected = !selected,
            onClick = onEvent2,
            modifier = Modifier.padding(start = 8.dp)

        )
        Text(
            text = text2,
            fontSize = 16.sp,
            style = TextStyle(color = Color.White, fontSize = 16.sp),
            modifier = Modifier.padding(start = 8.dp)
        )
    }




}
