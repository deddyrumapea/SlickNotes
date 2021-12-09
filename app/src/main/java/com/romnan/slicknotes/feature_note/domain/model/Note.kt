package com.romnan.slicknotes.feature_note.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.romnan.slicknotes.ui.theme.Lavender200
import com.romnan.slicknotes.ui.theme.Rose300
import com.romnan.slicknotes.ui.theme.Turquoise200
import com.romnan.slicknotes.ui.theme.Yellow300

@Entity
data class Note(
    @PrimaryKey val id: Int? = null,
    val title: String,
    val content: String,
    val timeStamp: Long,
    val reminderTimeStamp: Long? = null,
    val color: Int
) {
    companion object {
        val noteColors = listOf(Yellow300, Turquoise200, Lavender200, Rose300)
    }
}

class InvalidNoteException(message: String) : Exception(message)