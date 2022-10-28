package com.example.maria_study_app.domain.models

import java.util.*


data class CatFacts(
    val fact: String,
    val length: Int
)

fun CatFacts.toCatFactEntity(): CatFactEntity {
    return CatFactEntity(UUID.randomUUID().toString(), fact)
}
