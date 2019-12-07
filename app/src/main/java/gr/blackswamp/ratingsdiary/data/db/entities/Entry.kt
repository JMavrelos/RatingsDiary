package gr.blackswamp.ratingsdiary.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import gr.blackswamp.ratingsdiary.ui.model.EntryVO
import java.util.*

@Entity(tableName = "entries")
data class Entry(
    @PrimaryKey @ColumnInfo(name = "id", collate = ColumnInfo.NOCASE)  val id: UUID
    , @ColumnInfo(name = "name", index = true)  val name: String
    , @ColumnInfo(name = "date")  val date: Date
    , @ColumnInfo(name = "rating")  val rating: Int
    , @ColumnInfo(name = "created") val created: Date = Date(System.currentTimeMillis())
) 