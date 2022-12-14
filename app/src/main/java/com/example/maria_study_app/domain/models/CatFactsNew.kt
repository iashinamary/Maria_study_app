package com.example.maria_study_app.domain.models

data class CatFactsNew(
    val current_page: Int,
    val data: List<CatFacts>,
    val first_page_url: String,
    val from: Int,
    val last_page: Int,
    val last_page_url: String,
    val links: List<Link>,
    val next_page_url: String,
    val path: String,
    val per_page: String,
    val prev_page_url: Any,
    val to: Int,
    val total: Int
)