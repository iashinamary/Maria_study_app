package com.example.maria_study_app

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow

class FragmentOneVm(
    private val apiService: ApiService
): ViewModel() {

//    val scope = CoroutineScope(Job() + Dispatchers.IO)

    val factsLive = MutableLiveData<CatFactsNew>()

//    val factsFlow = MutableSharedFlow<CatFactsNew>(0, 500, onBufferOverflow = BufferOverflow.SUSPEND)
    val factsFlow: MutableStateFlow<CatFactsNew?> = MutableStateFlow(null)


    fun getFacts(limit: Int){
        viewModelScope.launch(Dispatchers.IO) {
            val facts = apiService.getInfo(limit)
//            factsLive.postValue(facts)
            factsFlow.tryEmit(facts)

        }
//        val result = viewModelScope.async {
//            return@async apiService.getInfo(limit)
//        }
//
//
//        viewModelScope.launch {
//            val facts = result.await()
//        }
    }
}