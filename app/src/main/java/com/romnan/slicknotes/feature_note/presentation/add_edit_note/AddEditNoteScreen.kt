package com.romnan.slicknotes.feature_note.presentation.add_edit_note

import androidx.compose.animation.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Mic
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.romnan.slicknotes.R
import com.romnan.slicknotes.feature_note.domain.model.Note
import com.romnan.slicknotes.feature_note.presentation.add_edit_note.components.NoteTextField
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@Composable
fun AddEditNoteScreen(
    navController: NavController,
    noteColor: Int,
    viewModel: AddEditNoteViewModel = hiltViewModel()
) {
    val titleState = viewModel.titleState.value
    val contentState = viewModel.contentState.value
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    val backgroundAnimatable = remember {
        Animatable(Color(if (noteColor != -1) noteColor else viewModel.colorState.value))
    }

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is AddEditNoteViewModel.UIEvent.SaveNote -> navController.navigateUp()
                is AddEditNoteViewModel.UIEvent.ShowSnackbar -> {
                    scaffoldState.snackbarHostState.showSnackbar(message = event.message)
                }
            }
        }
    }

    Scaffold(scaffoldState = scaffoldState) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colors.surface),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    onClick = { }
                ) {
                    Icon(
                        imageVector = Icons.Default.Mic,
                        contentDescription = stringResource(R.string.start_stop_speech_note)
                    )
                }
                Row(modifier = Modifier.padding(16.dp)) {
                    Note.noteColors.forEach { color ->
                        Box(
                            modifier = Modifier
                                .size(32.dp)
                                .shadow(4.dp, CircleShape)
                                .clip(CircleShape)
                                .background(color)
                                .border(
                                    width = 2.dp,
                                    color = if (viewModel.colorState.value == color.toArgb()) {
                                        Color.Black
                                    } else Color.Transparent,
                                    shape = CircleShape
                                )
                                .clickable {
                                    viewModel.onEvent(AddEditNoteEvent.ChangeColor(color.toArgb()))
                                    scope.launch {
                                        backgroundAnimatable.animateTo(
                                            targetValue = Color(color.toArgb()),
                                            animationSpec = tween(500)
                                        )
                                    }
                                }
                        )
                    }
                }
                IconButton(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    onClick = { viewModel.onEvent(AddEditNoteEvent.SaveNote) }
                ) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = stringResource(R.string.save_note)
                    )
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(backgroundAnimatable.value)
                    .padding(16.dp)
            ) {
                NoteTextField(
                    singleLine = true,
                    text = titleState.text,
                    hint = titleState.hint,
                    isHintVisible = titleState.isHintVisible,
                    textStyle = MaterialTheme.typography.h5,
                    onValueChange = { viewModel.onEvent(AddEditNoteEvent.EnterTitle(it)) },
                    onFocusChange = { viewModel.onEvent(AddEditNoteEvent.ChangeTitleFocus(it)) }
                )
                Spacer(modifier = Modifier.height(16.dp))
                NoteTextField(
                    text = contentState.text,
                    hint = contentState.hint,
                    isHintVisible = contentState.isHintVisible,
                    textStyle = MaterialTheme.typography.body1,
                    onValueChange = { viewModel.onEvent(AddEditNoteEvent.EnterContent(it)) },
                    onFocusChange = { viewModel.onEvent(AddEditNoteEvent.ChangeContentFocus(it)) },
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth()
                )
            }
        }

    }
}
