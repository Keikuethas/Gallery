package com.keikuethas.gallery

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun Description(description: MutableState<String>) {
    Row(
        modifier = Modifier.fillMaxSize(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        val redactingDescription = remember { mutableStateOf(false) }
        if (redactingDescription.value) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                OutlinedTextField(
                    value = description.value,
                    onValueChange = { description.value = it },
                    label = { Text("Description") },
                    modifier = Modifier
                        .width(240.dp)
                )
                IconButton(
                    onClick = {
                        redactingDescription.value = false
                    },
                    modifier = Modifier.padding(start = 15.dp)
                ) {
                    Icon(Icons.Default.Check, null)
                }
            }
        } else
            Text(
                description.value.ifBlank { "edit description..." },
                modifier = Modifier
                    .width(240.dp)
                    //.height(75.dp)
                    .clickable(true, onClick = {
                        redactingDescription.value = true
                    }),
                textAlign = TextAlign.Center,
                fontStyle = if (description.value.isBlank()) FontStyle.Italic else FontStyle.Normal
            )
    }
}