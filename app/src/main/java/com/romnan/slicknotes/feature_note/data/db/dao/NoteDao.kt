package com.romnan.slicknotes.feature_note.data.db.dao

import androidx.room.*
import com.romnan.slicknotes.feature_note.domain.model.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {
    @Query("SELECT * FROM note")
    fun findAll(): Flow<List<Note>>

    @Query("SELECT * FROM note WHERE id = :id")
    suspend fun find(id: Int): Note?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(note: Note)

    @Delete
    suspend fun delete(note: Note)
}