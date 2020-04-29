package com.example.testworkmanager

import android.app.Application
import androidx.work.Configuration

class TestApp: Application(), Configuration.Provider {
    override fun getWorkManagerConfiguration() =
        Configuration.Builder()
            .setMinimumLoggingLevel(android.util.Log.VERBOSE)
            .build()

}