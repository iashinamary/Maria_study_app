package com.example.maria_study_app

import android.content.Context
import android.os.Environment
import java.io.File
import java.io.IOException
import java.text.DateFormat

class Test() {


    /**
     * Сделать чтение безопасным, проверить работает ли запись, сделать этот класс синглтоном
     */

    private val logPath = Environment.DIRECTORY_DOCUMENTS + File.separator + "logs" + File.separator + "test.txt"

    private val file = File(logPath)

    fun logText(text : String) : Boolean{
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