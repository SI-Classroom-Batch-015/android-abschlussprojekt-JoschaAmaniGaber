package com.example.rickandmortyguide.ui

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.rickandmortyguide.data.Repository
import com.example.rickandmortyguide.data.local.getCharacterDb
import com.example.rickandmortyguide.data.local.getLocationDb
import com.example.rickandmortyguide.data.model.Character
import com.example.rickandmortyguide.data.remote.ApiStatus
import com.example.rickandmortyguide.data.remote.RickApi
import kotlinx.coroutines.launch

const val TAG = "MainViewModel"

class MainViewModel(application: Application): AndroidViewModel(application) {

    private val characterDb = getCharacterDb(application)
    private val locationDb = getLocationDb(application)
    private val repository = Repository(RickApi, characterDb, locationDb)

    private val _loading = MutableLiveData<ApiStatus>()
    val loading: LiveData<ApiStatus> get() = _loading

    val characters = repository.characters
    val locations = repository.locations

    private var _selectedCharacter = MutableLiveData<Character?>()
    val selectedCharacter: LiveData<Character?> get() = _selectedCharacter


    fun loadDatabases() {
            loadCharacters()
            loadLocations()
    }
    private fun loadCharacters() {
        viewModelScope.launch {
            _loading.value = ApiStatus.LOADINGCHARACTERS
            try {
                repository.loadCharacters()
                repository.loadCharactersPage()
                _loading.value = ApiStatus.CHARACTERSDONE
            } catch (e: Exception) {
                Log.e(TAG, "Error loading Data $e")
                if (characters.value.isNullOrEmpty()) {
                    _loading.value = ApiStatus.ERROR
                } else {
                    _loading.value = ApiStatus.CHARACTERSDONE
                }

            }

        }
    }

    private fun loadLocations() {
        viewModelScope.launch {
            _loading.value = ApiStatus.LOADINGLOCATION
            try {
                repository.loadLocations()
                repository.loadLocationsPage()
                _loading.value = ApiStatus.LOCATIONSDONE
            } catch (e:Exception) {
                Log.e(TAG, "Error loading Data $e")
                if (characters.value.isNullOrEmpty()) {
                    _loading.value = ApiStatus.ERROR
                } else {
                    _loading.value = ApiStatus.CHARACTERSDONE
                }
            }
        }
    }

    fun setSelectedCharacter(character: Character) {
        _selectedCharacter.value = character
    }

}