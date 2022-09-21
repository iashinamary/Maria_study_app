package com.example.maria_study_app

import androidx.lifecycle.LiveData

class Repository(
    private val dao: MyDao
) {

    fun insertNote(note: MyEntity){
        dao.insertNote(note)
    }

    fun deleteNote(note: MyEntity){
        dao.deleteNote(note)
    }

    fun getAllNotes(): LiveData<List<MyEntity>> = dao.getAllNotes()

    fun selectById(id: String): MyEntity = dao.selectById(id)

    fun getNotesCount(): Int = dao.getNotesCount()

    fun updateNoteText(text: String){
        dao.updateNoteText(text)
    }

    fun updateNote(note: MyEntity){
        dao.updateNote(note)
    }


}