package com.example.maria_study_app

import android.content.Context
import android.os.Environment
import android.util.Log
import java.io.File
import java.io.IOException
import java.text.DateFormat

class Test() {


    /**
     * Сделать чтение безопасным, проверить работает ли запись, сделать этот класс синглтоном
     */

    private val logPath = Environment.getExternalStorageDirectory().absolutePath + File.separator + "logs" + File.separator + "test.txt"



    private fun mkDir(){
        val path = Environment.getExternalStorageDirectory().absolutePath + File.separator + "logs"
        val dir = File(path)
        if(!dir.exists()){
            Log.d("@@@", "dir is not exist")
            val result  = dir.mkdirs()
            Log.d("@@@", result.toString())
        }
    }

    private val file = File(logPath)

    fun logText(text : String) : Boolean{
        mkDir()
        val currentTime = System.currentTimeMillis()
        val date =  DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT).format(currentTime)
        val handledText = "$date    $text\n".toByteArray(Charsets.US_ASCII)
        return try {
            if (file.exists() && file.isFile) {
                file.appendBytes(handledText)
            } else {
                file.createNewFile()
                file.appendBytes(handledText)
            }
            true
        } catch (e : IOException){
            e.printStackTrace()
            false
        }
    }

    fun readLogAsString() {
        readLogAsByteArray().decodeToString()
    }

    fun readLogAsByteArray() : ByteArray {
        file.readBytes()
    }

    fun readLines() : List<String> {
        file.readLines(Charsets.US_ASCII)
    }


}