package com.romnan.slicknotes.feature_note.presentation.notes.components

import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.romnan.slicknotes.R

@Composable
fun AddNoteFab(onClick: () -> Unit) {
    FloatingActionButton(onClick = onClick, backgroundColor = MaterialTheme.colors.primary) {
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = stringResource(R.string.add_note)
        )
    }
}
