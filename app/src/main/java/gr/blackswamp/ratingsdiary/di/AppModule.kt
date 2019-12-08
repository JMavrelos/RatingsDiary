package gr.blackswamp.ratingsdiary.di

import androidx.room.Room
import gr.blackswamp.core.logging.AppLog
import gr.blackswamp.core.logging.ILog
import gr.blackswamp.ratingsdiary.data.db.AppDatabase
import gr.blackswamp.ratingsdiary.data.db.IDatabase
import gr.blackswamp.ratingsdiary.data.db.prefs.AppPrefs
import gr.blackswamp.ratingsdiary.data.db.prefs.IPrefs
import gr.blackswamp.ratingsdiary.data.repos.IMainRepo
import gr.blackswamp.ratingsdiary.data.repos.MainRepo
import gr.blackswamp.ratingsdiary.vm.MainViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModules = module {
    //    single<IDispatchers> { AppDispatchers }
    single<ILog> { AppLog }
    single<IPrefs> { AppPrefs(androidApplication()) }
    single<IMainRepo> { MainRepo(get(), get()) }

    single<IDatabase> {
        Room.databaseBuilder(androidContext(), AppDatabase::class.java, AppDatabase.DATABASE)
            .build()
    }

    viewModel { MainViewModel(get(), androidApplication(), get()) }
}