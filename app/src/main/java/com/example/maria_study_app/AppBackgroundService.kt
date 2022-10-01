package com.example.maria_study_app

import android.app.Notification
import android.app.NotificationChannel
import android.app.Service
import android.content.Intent
import android.os.IBinder

class AppBackgroundService : Service() {

    companion object{
        const val ON_START_COMMAND = "start"
        const val ON_EXIT_COMMAND = "exit"
    }

    override fun onBind(intent: Intent?): IBinder? = null


    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when(intent!!.getStringExtra("")){
            ON_START_COMMAND -> {  }
            ON_EXIT_COMMAND -> {
                stopSelf()
            }
        }
        startForeground(0, Notification())
        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}