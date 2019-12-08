package gr.blackswamp.ratingsdiary.vm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import gr.blackswamp.core.TestDispatchers
import gr.blackswamp.core.lifecycle.SingleLiveEvent
import gr.blackswamp.core.logging.ILog
import gr.blackswamp.ratingsdiary.data.repos.IMainRepo
import gr.blackswamp.ratingsdiary.ui.commands.ScreenCommand
import gr.blackswamp.ratingsdiary.ui.model.EntryVO
import kotlinx.coroutines.*

class MainViewModel(private val repo: IMainRepo, app: Application, private val log: ILog) :
    AndroidViewModel(app) {
    private val mCommand = SingleLiveEvent<ScreenCommand>()
    private val mError = SingleLiveEvent<String>()
    private val mLoading = MutableLiveData<Boolean>(false)
    private val mEntries = MutableLiveData<List<EntryVO>>()

    val command: LiveData<ScreenCommand> = mCommand
    val error: LiveData<String> = mError
    val loading: LiveData<Boolean> = mLoading
    val entries: LiveData<List<EntryVO>> = mEntries


    fun test() {
        viewModelScope.launch {
            println("hello")
            val result = withContext(
                Dispatchers.IO
            ) {
                println("some heavy work")
                delay(1000)
                "world"
            }
            println("result is " + result)

        }
    }
}