package com.example.maria_study_app.data.remote.api

import com.example.maria_study_app.domain.models.CatFactsNew
import retrofit2.http.*

interface Api {

    @GET("/facts")
    suspend fun getInfo(
        @Query("limit")
        limit: Int
    ): CatFactsNew
//
//    @POST("/auth")
//    @FormUrlEncoded
//    suspend fun getToken(@Body credential: IdentityCredential) : AuthAnswer
//
//    @GET("/path")
//    suspend fun getUserPic(@Header(value = "") token: String): UserPicURL
//
//    @POST("/path/photo")
//    @Multipart
//    suspend fun loadPhoto(
//        @Header(value = "") token: String,
//        @Body userPic: PicContainer) : LoadState
//
//    @DELETE
//    suspend fun deleteById(@Body Id: Int)
//
//    @GET
//    suspend fun getFromElse(@Url newUrl: String)
}