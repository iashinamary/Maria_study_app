package com.example.maria_study_app.data_classes

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "facts")
data class CatFactEntity(
    @PrimaryKey
    val id: String,
    val fact: String
)
