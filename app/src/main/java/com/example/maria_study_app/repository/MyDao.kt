package com.example.maria_study_app.repository

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.maria_study_app.domain.models.MyEntity


@Dao
interface MyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNote(note: MyEntity)

    @Delete
    fun deleteNote(note: MyEntity)

    @Query("SELECT * FROM note_table")
    fun getAllNotes(): LiveData<List<MyEntity>>

    @Query("SELECT * FROM note_table WHERE id =:id LIMIT 1")
    fun selectById(id: String): MyEntity

    @Query("SELECT count(*) FROM note_table")
    fun getNotesCount(): Int

    @Query("UPDATE note_table SET text =:text")
    fun updateNoteText(text: String)

    @Update
    fun updateNote(note: MyEntity)

//
//    @Query("SELECT * FROM note_table LEFT JOIN (table) on (условие) UNION ORDER BY (по чему сортируем) DESC OFFSET LIMIT")
//
//    @Query("SELECT * FROM note_table WHERE id not in (SELECT id from note_table)")
}