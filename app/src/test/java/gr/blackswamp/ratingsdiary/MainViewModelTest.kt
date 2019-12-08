package gr.blackswamp.ratingsdiary

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import gr.blackswamp.core.MainCoroutineScopeRule
import gr.blackswamp.core.TestLog
import gr.blackswamp.ratingsdiary.data.repos.IMainRepo
import gr.blackswamp.ratingsdiary.vm.MainViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock


class MainViewModelTest {
    val repo = mock(IMainRepo::class.java)
    val app = mock(App::class.java)
    lateinit var vm :MainViewModel

    @ExperimentalCoroutinesApi
    @get:Rule
    val coroutineScope = MainCoroutineScopeRule()
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        vm = MainViewModel(repo, app, TestLog)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `tis but a test`() {
        runBlockingTest {
            vm.test()
            println("finished")
        }

    }
}