package com.keikuethas.gallery

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun IsImportant(important: MutableState<Boolean>) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(
            "Important",
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(end = 10.dp)
                .fillMaxHeight()
        )
        Switch(
            checked = important.value,
            onCheckedChange = { important.value = it },
        )

    }
}