package com.keikuethas.gallery

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableFloatState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun Rating(rating: MutableFloatState) {
    Row(verticalAlignment = Alignment.CenterVertically)
    {
        Text(
            "Rate this image:",
            modifier = Modifier.padding(end = 10.dp)
        )

        Slider(
            value = rating.floatValue,
            onValueChange = { rating.floatValue = it },
            steps = 3,
            valueRange = 1f..5f,
            modifier = Modifier.width(125.dp)
        )
        Text(
            "${rating.floatValue.toInt()}/5",
            modifier = Modifier.padding(start = 10.dp)
        )
    }
}