package com.example.maria_study_app

import android.content.Context
import android.os.Environment
import java.io.File

class Test(context : Context) {

    private val logPath = Environment.DIRECTORY_DOCUMENTS + File.separator + "logs" + File.separator + "test.txt"
//
//    val file = File(path)
//
//    fun foo(){
//        if(file.exists()){
//            file.appendBytes()
//        } else {
//            file.createNewFile()
//            file.appendBytes()
//        }
//
//        val str = "gfhgfhgfhgfyertygdfhdr".toByteArray(Charsets.UTF_8)
//    }

}