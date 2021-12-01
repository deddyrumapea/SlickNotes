package com.romnan.slicknotes.feature_note.presentation.util

sealed class Screen(val route: String) {
    object Notes : Screen("notes_screen")
    object AddEditNote : Screen("add_edit_note_screen") {
        const val ARG_NOTE_ID = "arg_note_id"
        const val ARG_NOTE_COLOR = "arg_note_color"
    }
}
