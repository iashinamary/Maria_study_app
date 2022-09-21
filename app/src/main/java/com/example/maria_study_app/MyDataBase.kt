package com.example.maria_study_app

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(
    version = 1,
    entities = [MyEntity::class]
)
abstract class MyDataBase : RoomDatabase(){

    abstract fun getMyDao(): MyDao
}