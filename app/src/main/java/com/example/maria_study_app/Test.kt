package com.example.maria_study_app

import android.content.Context
import java.io.File

class Test(context : Context) {

    private val path = "root" + File.separator + "appName" + File.separator + "gfgfgf"

    val file = File(path)

    fun foo(){
        if(file.exists()){
            file.appendBytes()
        } else {
            file.createNewFile()
            file.appendBytes()
        }

        val str = "gfhgfhgfhgfyertygdfhdr".toByteArray(Charsets.UTF_8)
    }
}