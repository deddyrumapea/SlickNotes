package com.romnan.slicknotes.feature_note.presentation.add_edit_note.components

import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Save
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.romnan.slicknotes.R

@Composable
fun SaveNoteFab(onClick: () -> Unit) {
    FloatingActionButton(
        backgroundColor = MaterialTheme.colors.primary,
        onClick = onClick
    ) {
        Icon(
            imageVector = Icons.Default.Save,
            contentDescription = stringResource(R.string.save_note)
        )
    }
}