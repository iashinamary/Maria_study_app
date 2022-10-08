package com.example.maria_study_app

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow


@Dao
interface FactsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFact(fact: CatFactEntity)

    @Query("SELECT * FROM facts")
    fun getAllFacts(): Flow<List<CatFactEntity>>

    @Query("SELECT * FROM facts WHERE (:query== '') OR (fact LIKE '%'|| :query || '%')")
    fun getFactsByQuery(query: String): Flow<List<CatFactEntity>>

    @Query("SELECT * FROM facts WHERE (:query== '') OR (fact LIKE '%'|| :query || '%')")
    fun getFactsByQuerySingle(query: String): List<CatFactEntity>

}