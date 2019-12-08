package gr.blackswamp.ratingsdiary.data.db.converters

import androidx.room.TypeConverter
import java.text.SimpleDateFormat
import java.util.*

class DateLongConverter {
    companion object {
        @TypeConverter
        @JvmStatic
        fun toDate(timestamp: Long): Date = Date(timestamp)

        @TypeConverter
        @JvmStatic
        fun toText(date: Date): Long = date.time
    }
}