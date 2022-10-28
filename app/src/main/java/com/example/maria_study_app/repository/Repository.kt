package com.example.maria_study_app.repository

import androidx.lifecycle.LiveData
import com.example.maria_study_app.domain.models.MyEntity

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