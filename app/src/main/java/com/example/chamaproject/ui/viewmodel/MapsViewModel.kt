package com.example.chamaproject.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.chamaproject.data.others.Spot
import com.example.chamaproject.data.repository.MapsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async


class MapsViewModel(private val repository: MapsRepository) : ViewModel() {

    suspend fun setResultSearch(tipo: String): List<Spot> {
        var spotList = listOf<Spot>()
        val job = CoroutineScope(Dispatchers.Main).async {
            spotList = repository.execute(tipo)
        }
        job.await()
        return spotList
    }
}

