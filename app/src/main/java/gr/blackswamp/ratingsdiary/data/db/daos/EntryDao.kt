package gr.blackswamp.ratingsdiary.data.db.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import gr.blackswamp.ratingsdiary.data.db.entities.Entry
import java.util.*

@Dao
interface EntryDao {

    @Query("select * from entries ")
    fun getEntries(): LiveData<List<Entry>>

    /**
     * inserts an entry and returns the row id
     */
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(entry: Entry): Long

    /**
     * inserts or replaces an existing entry
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun replace(entry: Entry): Long

    /**
     * deletes a number of entries and returns the affected rows
     */
    @Delete
    suspend fun delete(vararg entries: Entry): Int

    /**
     * deletes an entry with a specific id
     */
    @Query("delete from entries where id=:id")
    suspend fun delete(id: UUID): Int


}