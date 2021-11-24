package com.romnan.slicknotes.feature_note.domain.use_case

data class NoteUseCases(
    val save: SaveNote,
    val findAll: FindAllNotes,
    val find: FindNote,
    val delete: DeleteNote
)