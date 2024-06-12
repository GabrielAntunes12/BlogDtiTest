package com.example.blogdtitest.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.blogdtitest.repository.BlogRepository
import com.example.blogdtitest.ui.states.StatesRetrieveList
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivityViewModel(private val repository: BlogRepository) : ViewModel() {

    private var mSuccessRetrieveList = MutableLiveData<StatesRetrieveList>()
    var successRetrieveList: LiveData<StatesRetrieveList> = mSuccessRetrieveList

    fun retrieveList() {
        CoroutineScope(Dispatchers.Default).launch {
            repository.retrieveList{list, message ->
                when{
                    list.isNullOrEmpty() ->{
                        mSuccessRetrieveList.postValue(StatesRetrieveList.Error(message))
                    }
                    else ->{
                        mSuccessRetrieveList.postValue(StatesRetrieveList.SuccessRetrieveList(list))
                    }
                }
            }
        }
    }
}