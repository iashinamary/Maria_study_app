package com.example.maria_study_app.domain.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "note_table")
data class MyEntity(
    @PrimaryKey
    val id: String,
    val text: String,
    val title: String,
    @ColumnInfo(name = "time_stamp")
    val timeStamp: Long
)