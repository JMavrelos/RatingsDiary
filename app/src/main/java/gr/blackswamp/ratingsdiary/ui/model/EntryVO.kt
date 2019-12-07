package gr.blackswamp.ratingsdiary.ui.model

import java.util.*

data class EntryVO(
    val id: UUID,
    val description: String,
    val rating: Int,
    val date: Date
)