package gr.blackswamp.ratingsdiary.vm

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import gr.blackswamp.core.coroutines.IDispatchers
import gr.blackswamp.core.lifecycle.SingleLiveEvent
import gr.blackswamp.ratingsdiary.data.repos.IMainRepo
import gr.blackswamp.ratingsdiary.ui.commands.ScreenCommand
import gr.blackswamp.ratingsdiary.ui.model.EntryVO

class MainViewModel(private val repo: IMainRepo, app: Application, private val dispatchers: IDispatchers, private val log: Log) :
    AndroidViewModel(app) {
    private val mCommand = SingleLiveEvent<ScreenCommand>()
    private val mError = SingleLiveEvent<String>()
    private val mLoading = MutableLiveData<Boolean>(false)
    private val mEntries = MutableLiveData<List<EntryVO>>()

    val command: LiveData<ScreenCommand> = mCommand
    val error: LiveData<String> = mError
    val loading: LiveData<Boolean> = mLoading
    val entries: LiveData<List<EntryVO>> = mEntries

}