package com.example.maria_study_app.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.maria_study_app.*
import com.example.maria_study_app.api.ApiService
import com.example.maria_study_app.data_classes.CatFactsNew
import com.example.maria_study_app.repository.FactsRepository
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import java.lang.Exception

class FragmentOneVm(
    private val apiService: ApiService,
    private val repo: FactsRepository
) : ViewModel() {

//    val scope = CoroutineScope(Job() + Dispatchers.IO)

    val factsLive = MutableLiveData<CatFactsNew>()

    private val userQueryFlow = MutableStateFlow("")

    //    val factsFlow = MutableSharedFlow<CatFactsNew>(0, 500, onBufferOverflow = BufferOverflow.SUSPEND)
    val factsFlow: StateFlow<List<CatFacts>> = userQueryFlow.flatMapLatest { query->
        repo.getFactsByQuery(query)
    }
        .map { list->
            list.map{ entity->
                CatFacts(
                    entity.fact,
                    entity.fact.length
                )
            }
        }
        .stateIn(viewModelScope, SharingStarted.Eagerly, repo.getFactsByQuerySingle(userQueryFlow.value).map { CatFacts(it.fact, it.fact.length) })


    fun getFacts(limit: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val facts = apiService.getInfo(limit)
                facts.data.forEach {
                    repo.addFact(it.toCatFactEntity())
                }
            } catch (e : Exception){
                e.printStackTrace()
            } finally {

            }

        }
    }

    fun setNewQuery(newText: String?) {
        newText?.let {
            Log.d("@@@", "VM: $it")
            userQueryFlow.value = newText
        }
    }
}