package com.example.maria_study_app.data_classes

import com.example.maria_study_app.data_classes.CatFactEntity
import java.util.*


data class CatFacts(
    val fact: String,
    val length: Int
)

fun CatFacts.toCatFactEntity(): CatFactEntity {
    return CatFactEntity(UUID.randomUUID().toString(), fact)
}
