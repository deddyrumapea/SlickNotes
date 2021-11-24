package com.romnan.slicknotes.feature_note.domain.repository

import com.romnan.slicknotes.feature_note.domain.model.Note
import kotlinx.coroutines.flow.Flow

interface NoteRepository {
    fun findAll(): Flow<List<Note>>

    suspend fun find(id: Int): Note?

    suspend fun save(note: Note)

    suspend fun delete(note: Note)
}