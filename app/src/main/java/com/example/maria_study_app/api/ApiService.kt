package com.example.maria_study_app.api

import com.example.maria_study_app.api.Api

class ApiService(private val api: Api) {

    suspend fun getInfo(limit: Int) = api.getInfo(limit)

}