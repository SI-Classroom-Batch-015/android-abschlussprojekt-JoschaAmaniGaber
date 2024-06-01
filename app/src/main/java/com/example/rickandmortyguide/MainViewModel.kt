package com.example.rickandmortyguide.ui

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.rickandmortyguide.data.Repository
import com.example.rickandmortyguide.data.local.getDatabase
import com.example.rickandmortyguide.data.model.Character
import com.example.rickandmortyguide.data.remote.ApiStatus
import com.example.rickandmortyguide.data.remote.RickApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

const val TAG = "MainViewModel"

class MainViewModel(application: Application): AndroidViewModel(application) {

    private val database = getDatabase(application)
    private val repository = Repository(RickApi, database)

    private val waitingTime: Long = 888 // millieseconds
    private var job: Job? = null

    private val _loading = MutableLiveData<ApiStatus>()
    val loading: LiveData<ApiStatus> get() = _loading

    val characters = repository.characters

    private var _character = MutableLiveData<Character>()
    val character: LiveData<Character> get() = _character

    init {
        getCharacters()
    }

    private fun getCharacters() {
        viewModelScope.launch {
            _loading.value = ApiStatus.LOADING
            try {
                repository.getCharacters()

                _loading.value = ApiStatus.DONE
            } catch (e: Exception) {
                Log.e(TAG, "Error loading Data $e")
                if (characters.value.isNullOrEmpty()) {
                    _loading.value = ApiStatus.ERROR
                } else {
                    _loading.value = ApiStatus.DONE
                }

            }

        }
    }

    fun getCharacterById(id: Int): Character? {
        return characters.value?.find { it.id == id }
    }

    fun getApiCharacterById(id: Int) {
        viewModelScope.launch {
            _loading.value= ApiStatus.LOADING
            try {
                repository.getCharacterById(id)
                _loading.value = ApiStatus.DONE
            } catch (e: Exception) {
                Log.e(TAG, "Error loading Data $e")
            }
        }

    }


}