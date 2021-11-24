package com.romnan.slicknotes.feature_note.domain.use_case

import com.romnan.slicknotes.feature_note.domain.model.Note
import com.romnan.slicknotes.feature_note.domain.repository.NoteRepository

class FindNote(private val noteRepository: NoteRepository) {
    suspend operator fun invoke(id: Int): Note? = noteRepository.find(id)
}