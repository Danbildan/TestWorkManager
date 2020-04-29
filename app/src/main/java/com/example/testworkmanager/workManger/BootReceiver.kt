package com.example.testworkmanager.workManger

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class BootReceiver: BroadcastReceiver() {


    @SuppressLint("UnsafeProtectedBroadcastReceiver")
    override fun onReceive(context: Context?, intent: Intent?) {
        if(context != null) {
            Toast.makeText(context, "tooooast!", Toast.LENGTH_LONG).show()
        }
    }
}