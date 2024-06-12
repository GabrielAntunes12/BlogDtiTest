package com.example.blogdtitest.ui.states

import com.example.blogdtitest.model.ItemPostData

sealed class StatesAddPost {
    data class SuccessAddPost(var message: String) : StatesAddPost()

    data class Error(var message: String) : StatesAddPost()
}