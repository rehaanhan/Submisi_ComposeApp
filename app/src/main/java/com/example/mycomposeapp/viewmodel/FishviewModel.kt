package com.example.mycomposeapp.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.mycomposeapp.data.FishRepository
import com.example.mycomposeapp.model.Ikan
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class FishViewModel(private val repository: FishRepository) : ViewModel(){
    private val _groupedFish = MutableStateFlow(
        repository.getAllFish()
            .sortedBy { it.name }
            .groupBy { it.name[0] }
    )
    val groupedFish: StateFlow<Map<Char, List<Ikan>>> get() = _groupedFish

    private val _query = mutableStateOf("")
    val query: State<String> get() = _query

    fun search (newQuery: String){
        _query.value = newQuery
        _groupedFish.value = repository.searchIkan(_query.value)
            .sortedBy { it.name }
            .groupBy { it.name[0] }
    }

    fun searchById(id: String): Ikan {
        return repository.searchFishById(id)
    }
}