package com.example.maria_study_app.repository

import com.example.maria_study_app.domain.models.CatFactEntity

class FactsRepository(private val dao: FactsDao) {

    suspend fun addFact(fact: CatFactEntity) = dao.addFact(fact)

    fun getAllFacts() = dao.getAllFacts()

    fun getFactsByQuery(query: String) = dao.getFactsByQuery(query)

    fun getFactsByQuerySingle(query: String) = dao.getFactsByQuerySingle(query)

}