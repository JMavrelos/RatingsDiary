package gr.blackswamp.ratingsdiary.data.db.daos

import androidx.room.*
import gr.blackswamp.ratingsdiary.data.db.entities.Entry
import java.util.*

@Dao
interface EntryDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(entry: Entry): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun replace(entry: Entry): Long

    @Delete
    suspend fun delete(vararg entries: Entry): Int

    @Query("delete from entries where id=:id")
    suspend fun delete(id: UUID): Int


}