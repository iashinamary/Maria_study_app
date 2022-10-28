package com.example.maria_study_app.repository

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.maria_study_app.domain.models.CatFactEntity
import com.example.maria_study_app.domain.models.MyEntity


@Database(
    version = 1,
    entities = [MyEntity::class, CatFactEntity::class]
)
abstract class MyDataBase : RoomDatabase(){

    abstract fun getMyDao(): MyDao

    abstract fun getFactsDao() : FactsDao
}