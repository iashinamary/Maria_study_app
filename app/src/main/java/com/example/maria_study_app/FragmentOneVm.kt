package com.example.maria_study_app

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import java.util.*

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
            val facts = apiService.getInfo(limit)
            facts.data.forEach {
                repo.addFact(it.toCatFactEntity())
            }
        }
    }

    fun setNewQuery(newText: String?) {
        newText?.let {
            userQueryFlow.value = newText
        }
    }
}