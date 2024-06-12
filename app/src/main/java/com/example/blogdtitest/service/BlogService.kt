package com.example.blogdtitest.service

import com.example.blogdtitest.model.ItemPostData
import com.google.gson.JsonObject
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Url

interface BlogService {
    @GET("posts")
    suspend fun getPosts() : Response<List<ItemPostData>>

    @GET("posts/{id}")
    suspend fun getUniquePost(@Path("id")idPost: Int) : Response<ItemPostData>

    @POST("posts")
    suspend fun publishPost(@Body itemPostData: ItemPostData) : Response<JsonObject>

}