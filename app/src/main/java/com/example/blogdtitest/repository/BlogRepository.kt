package com.example.blogdtitest.repository

import android.content.SharedPreferences
import apimocked.ApiMocked
import com.example.blogdtitest.model.ItemPostData
import com.example.blogdtitest.service.BlogService
import com.example.blogdtitest.util.API_DECIDED_REST

class BlogRepository(private val blogService: BlogService, private val srp: SharedPreferences) {

    private val apiMocked = ApiMocked(srp)
    suspend fun retrieveList(result: (List<ItemPostData>?, String) -> Unit) {
        if (srp.getBoolean(API_DECIDED_REST, false)){
            val resultHere = blogService.getPosts()
            when (resultHere.isSuccessful) {
                true -> {
                    result((resultHere.body() as List<ItemPostData>), resultHere.message())
                }

                false -> result(null, resultHere.message())
            }
        }else{
            result(apiMocked.getPosts(), "MockedAPI")
        }
    }

    suspend fun addPost(itemPostData: ItemPostData, result: (Boolean, String) -> Unit) {
        if (srp.getBoolean(API_DECIDED_REST, false)){
            val resultHere = blogService.publishPost(itemPostData)
            when (resultHere.isSuccessful) {
                true -> {
                    result(true, resultHere.message())
                }

                false -> result(false, resultHere.message())
            }
        }else{
            result(apiMocked.publishPost(itemPostData), "MockedAPI")
        }
    }
}