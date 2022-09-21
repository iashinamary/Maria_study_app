package com.example.maria_study_app

import android.bluetooth.BluetoothSocket
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DataTransfer(val socket: BluetoothSocket) {

    val input = socket.inputStream
    val output = socket.outputStream
    val buffer = mutableListOf<Int>()


    fun startConnect() {
        CoroutineScope(Dispatchers.IO).launch {
            while (true) {
                while (input.available() > 0) {
                    buffer.add(input.read())
                }
            }
        }
    }
}