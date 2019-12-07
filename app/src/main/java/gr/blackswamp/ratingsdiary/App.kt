package gr.blackswamp.ratingsdiary

import android.app.Application
import gr.blackswamp.ratingsdiary.di.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            if (BuildConfig.DEBUG)
                androidLogger(Level.INFO)
            androidContext(this@App)
            modules(appModules)
        }
    }
}