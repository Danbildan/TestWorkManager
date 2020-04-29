package com.example.testworkmanager.workManger

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.testworkmanager.PushNotifier
import okhttp3.*
import java.io.IOException

class TestWorker(appContext: Context, workerParameters: WorkerParameters)
    : CoroutineWorker(appContext, workerParameters) {

    private val TAG = this.javaClass.name

    private val pushNotifier = PushNotifier(appContext)

    override suspend fun doWork(): Result {
        pushNotifier.notify("kek")
        val success = load()

        return if (success) Result.success() else Result.retry()
    }


    private fun load(): Boolean {
        val client = OkHttpClient()
        val url = "https://publicobject.com/helloworld.txt"

        val request = Request.Builder()
            .url(url)
            .build()
        try {
            client.newCall(request).execute().use { response ->
                Log.d(TAG, "load is successful: ${response.isSuccessful}")
                return response.isSuccessful
            }
        }catch (e: IOException) {
            Log.d(TAG, "load failed")
            return false
        }
    }
}