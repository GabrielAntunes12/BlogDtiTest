package com.example.blogdtitest.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.blogdtitest.model.ItemPostData
import com.example.blogdtitest.repository.BlogRepository
import com.example.blogdtitest.ui.states.StatesAddPost
import com.example.blogdtitest.ui.states.StatesRetrieveList
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NewPostViewModel(private val repository: BlogRepository) : ViewModel() {

    private var mStateAddPost = MutableLiveData<StatesAddPost>()
    var stateAddPost: LiveData<StatesAddPost> = mStateAddPost

    private fun verifyFields(title: String, desc: String): Boolean {
        return !(title.isEmpty() || desc.isEmpty())
    }

    fun addPost(title: String, desc: String) {
        if (verifyFields(title, desc)) {
            CoroutineScope(Dispatchers.Default).launch {
                repository.addPost(ItemPostData(title, desc)) { success, message ->
                    when (success) {
                        true -> {
                            mStateAddPost.postValue(StatesAddPost.SuccessAddPost(message))
                        }

                        false -> {
                            mStateAddPost.postValue(StatesAddPost.Error(message))
                        }
                    }
                }
            }
        }else mStateAddPost.postValue(StatesAddPost.Error("Preencha todos os campos."))
    }
}