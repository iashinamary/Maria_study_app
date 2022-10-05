package com.example.maria_study_app

import androidx.room.Query

class FactsRepository(private val dao: FactsDao) {

    suspend fun addFact(fact: CatFactEntity) = dao.addFact(fact)

    fun getAllFacts() = dao.getAllFacts()

    fun getFactsByQuery(query: String) = dao.getFactsByQuery(query)

    fun getFactsByQuerySingle(query: String) = dao.getFactsByQuerySingle(query)

}