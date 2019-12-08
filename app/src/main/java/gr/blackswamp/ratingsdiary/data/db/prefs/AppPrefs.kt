package gr.blackswamp.ratingsdiary.data.db.prefs

import android.app.Application
import android.content.Context.MODE_PRIVATE

class AppPrefs(application: Application) : IPrefs {
    companion object {
        const val TAG = "AppPrefs"
        private const val NAME = TAG
    }

    private val prefs = application.getSharedPreferences(NAME, MODE_PRIVATE)

}