package com.romnan.slicknotes.di

import android.app.Application
import androidx.room.Room
import com.romnan.slicknotes.feature_note.data.db.SlickNotesDatabase
import com.romnan.slicknotes.feature_note.data.repository.NoteRepositoryImpl
import com.romnan.slicknotes.feature_note.domain.repository.NoteRepository
import com.romnan.slicknotes.feature_note.domain.use_case.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideSlickNotesDatabase(app: Application): SlickNotesDatabase {
        return Room.databaseBuilder(
            app,
            SlickNotesDatabase::class.java,
            SlickNotesDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideNoteRepository(db: SlickNotesDatabase): NoteRepository {
        return NoteRepositoryImpl(db.noteDao)
    }

    @Provides
    @Singleton
    fun provideNoteUseCases(repository: NoteRepository): NoteUseCases {
        return NoteUseCases(
            save = SaveNote(repository),
            findAll = FindAllNotes(repository),
            find = FindNote(repository),
            delete = DeleteNote(repository)
        )
    }
}