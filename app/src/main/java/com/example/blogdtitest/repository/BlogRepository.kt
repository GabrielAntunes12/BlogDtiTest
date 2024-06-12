package com.example.blogdtitest.repository

import com.example.blogdtitest.model.ItemPostData
import com.example.blogdtitest.service.BlogService

class BlogRepository(private val blogService: BlogService) {

    suspend fun retrieveList(result: (List<ItemPostData>?, String) -> Unit) {
        val resultHere = blogService.getPosts()
        when (resultHere.isSuccessful) {
            true -> {
                result((resultHere.body() as List<ItemPostData>), resultHere.message())
            }

            false -> result(null, resultHere.message())
        }
    }

    suspend fun addPost(itemPostData: ItemPostData, result: (Boolean, String) -> Unit) {
        val resultHere = blogService.publishPost(itemPostData)
        when (resultHere.isSuccessful) {
            true -> {
                result(true, resultHere.message())
            }

            false -> result(false, resultHere.message())
        }
    }

}