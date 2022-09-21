package com.example.maria_study_app

class Fact {

    val list = listOf(1, 2 , 45, 77, 85)

    fun foo(){
        list.subList(1, list.size)
        val new = list.map { it * 1.0f }
        list.first()
        list.last()
        val filtered = list.filter { it%2 == 0 }
        list.sortedBy { it }

        val str = "1ojidaj1-id-aoa"
        str.toByteArray(Charsets.US_ASCII)
        
    }
}