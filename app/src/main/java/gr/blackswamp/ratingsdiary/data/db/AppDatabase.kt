package gr.blackswamp.ratingsdiary.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import gr.blackswamp.ratingsdiary.data.db.converters.DateConverter
import gr.blackswamp.ratingsdiary.data.db.converters.UUIDConverter
import gr.blackswamp.ratingsdiary.data.db.daos.EntryDao
import gr.blackswamp.ratingsdiary.data.db.entities.Entry


@Database(
    entities = [Entry::class]
    , version = 1
)
@TypeConverters(DateConverter::class, UUIDConverter::class)
abstract class AppDatabase : RoomDatabase(), IDatabase {
    companion object {
        const val DATABASE = "data.db"
    }

    abstract val reportDao: EntryDao
}