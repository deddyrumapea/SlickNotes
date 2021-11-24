package com.romnan.slicknotes.feature_note.domain.use_case

import com.romnan.slicknotes.feature_note.domain.model.InvalidNoteException
import com.romnan.slicknotes.feature_note.domain.model.Note
import com.romnan.slicknotes.feature_note.domain.repository.NoteRepository

class SaveNote(private val noteRepository: NoteRepository) {

    @Throws(InvalidNoteException::class)
    suspend operator fun invoke(note: Note) {
        if (note.title.isBlank()) {
            throw InvalidNoteException("The title of the note can't be empty.")
        }
        if (note.content.isBlank()) {
            throw InvalidNoteException("The content of the note can't be empty.")
        }
        noteRepository.save(note)
    }
}