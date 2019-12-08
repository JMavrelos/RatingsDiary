package gr.blackswamp.ratingsdiary

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import gr.blackswamp.core.MainCoroutineScopeRule
import gr.blackswamp.ratingsdiary.data.db.IDatabase
import gr.blackswamp.ratingsdiary.data.db.prefs.IPrefs
import gr.blackswamp.ratingsdiary.data.repos.MainRepo
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.reset

class MainRepoTest {
    private val db: IDatabase = mock(IDatabase::class.java)
    private val prefs: IPrefs = mock(IPrefs::class.java)
    private val repo = MainRepo(db, prefs)

    @ExperimentalCoroutinesApi
    @get:Rule
    val coroutineScope = MainCoroutineScopeRule()
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        reset(db, prefs)
    }

//    @Test
//    fun

}