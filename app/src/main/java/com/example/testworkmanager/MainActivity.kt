package com.example.testworkmanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.work.*
import com.example.testworkmanager.workManger.TestWorker
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()

        startWork( isPeriodic = true)
    }

    private fun startWork(isPeriodic: Boolean) {
        if (isPeriodic) startPeriodicWork() else startOneTimeWork()
    }

    private fun startPeriodicWork() {
        WorkManager.getInstance(this).enqueueUniquePeriodicWork(
            "TestWorker",
            ExistingPeriodicWorkPolicy.REPLACE,
            createPeriodicRequest()
        )
    }

    private fun startOneTimeWork() {
        WorkManager.getInstance(this).enqueueUniqueWork(
            "TestWorker",
            ExistingWorkPolicy.REPLACE,
            createOneTimeRequest()
        )
    }

    private fun createPeriodicRequest() : PeriodicWorkRequest {
        val constrains = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        return PeriodicWorkRequestBuilder<TestWorker>(15, TimeUnit.MINUTES)
            .addTag("TestWorker")
            .setConstraints(constrains)
            .build()

    }

    private fun createOneTimeRequest() : OneTimeWorkRequest {
        val constrains = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()


        return OneTimeWorkRequestBuilder<TestWorker>()
            .addTag("TestWorker")
            .setInitialDelay(3, TimeUnit.MINUTES)
            .setConstraints(constrains)
            .build()
    }
}
