package com.example.maria_study_app

class FactsRepository(private val dao: FactsDao) {

    suspend fun addFact(fact: CatFactEntity) = dao.addFact(fact)

    fun getAllFacts() = dao.getAllFacts()

}