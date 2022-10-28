package com.example.maria_study_app.data.remote.api


class ApiService(private val api: Api) {

    suspend fun getInfo(limit: Int) = api.getInfo(limit)

}