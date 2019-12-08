package gr.blackswamp.ratingsdiary

import android.database.sqlite.SQLiteConstraintException
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import gr.blackswamp.core.testing.randomString
import gr.blackswamp.core.util.toTimestamp
import gr.blackswamp.ratingsdiary.data.db.AppDatabase
import gr.blackswamp.ratingsdiary.data.db.entities.Entry
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import java.util.*
import kotlin.random.Random

@RunWith(RobolectricTestRunner::class)
@Config(application = TestApp::class)
class AppDatabaseTest {
    val context = InstrumentationRegistry.getInstrumentation().targetContext
    lateinit var db: AppDatabase
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java)
            .allowMainThreadQueries()
            .build()
    }


    @Test
    @ExperimentalCoroutinesApi
    fun `test that insert works`() {
        runBlockingTest {
            val entry = buildEntry()


            val result = db.dao.insert(entry)
            println(result)

            db.openHelper.readableDatabase.query("select * from entries").use {
                assertEquals(1, it.count)
                assertTrue(it.moveToFirst())
                assertEquals(entry.id.toString(), it.getString(it.getColumnIndex("id")))
                assertEquals(entry.name, it.getString(it.getColumnIndex("name")))
                assertEquals(entry.date.toTimestamp(), it.getString(it.getColumnIndex("date")))
                assertEquals(entry.rating, it.getInt(it.getColumnIndex("rating")))
            }
        }

    }

    @Test
    @ExperimentalCoroutinesApi
    fun `test that delete works`() {
        runBlockingTest {
            val entry1 = buildEntry()
            println(db.dao.insert(entry1))
            val entry2 = buildEntry()
            println(db.dao.insert(entry2))
            db.dao.delete(entry1.id)

            db.openHelper.readableDatabase.query("select * from entries").use {
                assertEquals(1, it.count)
                assertTrue(it.moveToFirst())
                assertEquals(entry2.id.toString(), it.getString(it.getColumnIndex("id")))
                assertEquals(entry2.name, it.getString(it.getColumnIndex("name")))
                assertEquals(entry2.date.toTimestamp(), it.getString(it.getColumnIndex("date")))
                assertEquals(entry2.rating, it.getInt(it.getColumnIndex("rating")))
            }

        }
    }

    @Test(expected = SQLiteConstraintException::class)
    @ExperimentalCoroutinesApi
    fun `test that no duplicate ids can be inserted`() {
        runBlockingTest {
            val entry = buildEntry()

            val result = db.dao.insert(entry)
            println(result)
            db.dao.insert(entry)
        }
    }

    @Test
    @ExperimentalCoroutinesApi
    fun `test that replace works`() {
        runBlockingTest {
            val entry = buildEntry()

            db.dao.insert(entry)

            val entry2 = entry.copy(name = "Testing stuff", rating = 12)

            println(db.dao.replace(entry2))

            db.openHelper.readableDatabase.query("select * from entries").use {
                assertEquals(1, it.count)
                assertTrue(it.moveToFirst())
                assertEquals(entry2.id.toString(), it.getString(it.getColumnIndex("id")))
                assertEquals(entry2.name, it.getString(it.getColumnIndex("name")))
                assertEquals(entry2.date.toTimestamp(), it.getString(it.getColumnIndex("date")))
                assertEquals(entry2.rating, it.getInt(it.getColumnIndex("rating")))
            }
        }
    }


    @Test
    @ExperimentalCoroutinesApi
    fun `test that mass delete works`() {
        runBlockingTest {
            val entities = (1..100).map { buildEntry() }

            entities.forEach {
                db.dao.insert(it)
            }

            val deleted = (1..20).map { Random.nextInt(1, 100) }.map { entities[it] }.distinct()

            println("deleting ${deleted.size} entries")

            db.dao.delete(*deleted.toTypedArray())

            db.openHelper.readableDatabase.query("select * from entries").use {
                assertEquals(100 - deleted.size, it.count)
                val deletedIds = deleted.map { it.id.toString() }
                val allIds = entities.map { it.id.toString() }
                assertTrue(it.moveToFirst())
                do {
                    val id = it.getString(it.getColumnIndex("id"))
                    assertFalse(deletedIds.contains(id))
                    assertTrue(allIds.contains(it.getString(it.getColumnIndex("id"))))

                } while (it.moveToNext())
            }
        }
    }

    @Test
    @ExperimentalCoroutinesApi
    fun `test livedata is ok`() {
        runBlockingTest {
            val entities = (1..4).map { buildEntry() }
            val liveData = db.dao.getEntries()
            liveData.observeForever {}

            for (i in entities.indices) {
                db.dao.insert(entities[i])
                assertEquals(i + 1, liveData.value!!.size)
            }
        }
    }

    @Test(expected = SQLiteConstraintException::class)
    @ExperimentalCoroutinesApi

    fun `make sure no duplicates are inserted`() {
        runBlockingTest {
            val entry = buildEntry()
            val duplicate = buildEntry().copy(name = entry.name, date = entry.date)

            db.dao.insert(entry)

            db.dao.insert(duplicate)
        }
    }

    private fun buildEntry(): Entry {
        return Entry(
            UUID.randomUUID()
            , randomString(10)
            , Date()
            , Random.nextInt(0, 10)
        )
    }

    @After
    fun tearDown() {
        db.close()
    }
}