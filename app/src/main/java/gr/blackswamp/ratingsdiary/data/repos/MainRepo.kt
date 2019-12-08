package gr.blackswamp.ratingsdiary.data.repos

import gr.blackswamp.core.data.Response
import gr.blackswamp.ratingsdiary.data.db.IDatabase
import gr.blackswamp.ratingsdiary.data.db.entities.Entry
import gr.blackswamp.ratingsdiary.data.db.prefs.IPrefs
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception
import java.util.*

class MainRepo(val db: IDatabase, val prefs: IPrefs) : IMainRepo {

    fun getEntries() = db.dao.getEntries()

    suspend fun saveEntry(name: String, date: Date, rating: Int): Response<Long> {
        return try {
            val entry = Entry(UUID.randomUUID(), name, date, rating)
            withContext(Dispatchers.IO) {
                val response = db.dao.insert(entry)
                Response.success(response)
            }
        } catch (t: Throwable) {
            Response.failure(t)
        }
    }

    suspend fun deleteEntry(id: UUID): Response<Int> {
        return try {
            withContext(Dispatchers.IO) {
                val response = db.dao.delete(id)
                Response.success(response)
            }
        } catch (t: Throwable) {
            Response.failure(t)
        }
    }
}