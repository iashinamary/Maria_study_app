package com.example.maria_study_app

import androidx.room.Entity
import java.util.*

@Entity
data class CatFacts(
    val fact: String,
    val length: Int
)

fun CatFacts.toCatFactEntity(): CatFactEntity{
    return CatFactEntity(UUID.randomUUID().toString(), fact)
}
