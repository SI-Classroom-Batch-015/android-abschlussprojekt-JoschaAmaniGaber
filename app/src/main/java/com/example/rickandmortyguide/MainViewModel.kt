package com.example.rickandmortyguide.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmortyguide.data.Repository
import com.example.rickandmortyguide.data.remote.RickApi
import kotlinx.coroutines.launch

class MainViewModel(
    private val repository: Repository = Repository(RickApi)
): ViewModel() {

    val characters = repository.characters

    init {
        loadCharacters()
    }

    private fun loadCharacters() {
        viewModelScope.launch {
            repository.loadCharacters()
        }
    }


}