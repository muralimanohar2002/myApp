package com.example.myapp.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapp.Models.RecyclerList
import com.example.myapp.network.RetroService
import com.example.myapp.network.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NewsFragmentViewModel: ViewModel() {
    var recyclerListLiveData: MutableLiveData<RecyclerList> = MutableLiveData()

    fun getRecyclerListObserver(): MutableLiveData<RecyclerList>{
        return recyclerListLiveData
    }

    fun makeApiCall(){
        viewModelScope.launch(Dispatchers.IO) {
            val retroInstance = RetrofitInstance.getRetroInstance().create(RetroService::class.java)
            val response = retroInstance.getDataFromApi("wsj.com", "4f8e3af2999e4fc381fff823ea320d5f")
            recyclerListLiveData.postValue(response)
        }
    }
}