package com.romnan.slicknotes.feature_note.presentation.notes.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Sort
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.romnan.slicknotes.R

@Composable
fun NotesScreenAppBar(onToggleOrderSection: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = stringResource(R.string.app_name), style = MaterialTheme.typography.h6)
        IconButton(onClick = onToggleOrderSection) {
            Icon(
                imageVector = Icons.Default.Sort,
                contentDescription = stringResource(R.string.sort)
            )
        }
    }
}
