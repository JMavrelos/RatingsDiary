package gr.blackswamp.ratingsdiary.vm

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import gr.blackswamp.core.coroutines.IDispatchers
import gr.blackswamp.core.lifecycle.SingleLiveEvent
import gr.blackswamp.ratingsdiary.data.repos.IMainRepo

class MainViewModel(private val repo: IMainRepo, app: Application, private val dispatchers: IDispatchers, private val log: Log) :
    AndroidViewModel(app), ListViewModel, DetailViewModel {

    private val mError = SingleLiveEvent<String>()
    val error: LiveData<String> = mError
    private val mLoading = MutableLiveData<Boolean>(false)
    val loading: LiveData<Boolean> = mLoading


    //region list view model
    //endregion

    //region detail view model

    //endregion
}