package com.romnan.slicknotes.feature_note.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.romnan.slicknotes.feature_note.data.db.dao.NoteDao
import com.romnan.slicknotes.feature_note.domain.model.Note

@Database(
    entities = [Note::class],
    version = 2
)
abstract class SlickNotesDatabase : RoomDatabase() {
    companion object{
        const val DATABASE_NAME = "slick_notes_db"
    }
    abstract val noteDao: NoteDao
}