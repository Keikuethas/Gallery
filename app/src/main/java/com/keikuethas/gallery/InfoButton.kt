package com.keikuethas.gallery

import android.content.Context
import android.widget.Toast
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable

@Composable
fun InfoButton(context: Context, text:String) {
    IconButton(
        onClick = {
            Toast.makeText(
                context, text, Toast.LENGTH_SHORT
            ).show()
        },
    ) {
        Icon(Icons.Default.Info, null)
    }
}