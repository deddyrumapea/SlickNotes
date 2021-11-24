package com.romnan.slicknotes.feature_note.data.repository

import com.romnan.slicknotes.feature_note.data.db.dao.NoteDao
import com.romnan.slicknotes.feature_note.domain.model.Note
import com.romnan.slicknotes.feature_note.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow

class NoteRepositoryImpl(private val noteDao: NoteDao) : NoteRepository {
    override fun findAll(): Flow<List<Note>> = noteDao.findAll()

    override suspend fun find(id: Int): Note? = noteDao.find(id)

    override suspend fun save(note: Note) = noteDao.save(note)

    override suspend fun delete(note: Note) = noteDao.delete(note)
}