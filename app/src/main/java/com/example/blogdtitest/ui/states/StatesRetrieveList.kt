package com.example.blogdtitest.ui.states

import com.example.blogdtitest.model.ItemPostData

sealed class StatesRetrieveList {
    data class SuccessRetrieveList(var list: List<ItemPostData>) : StatesRetrieveList()

    data class Error(var message: String) : StatesRetrieveList()

}
