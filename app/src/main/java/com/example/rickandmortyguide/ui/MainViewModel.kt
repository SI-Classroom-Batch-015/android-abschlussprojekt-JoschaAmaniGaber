package com.example.rickandmortyguide.ui

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.rickandmortyguide.data.Repository
import com.example.rickandmortyguide.data.local.getRickDb
import com.example.rickandmortyguide.data.model.character.Character
import com.example.rickandmortyguide.data.remote.ApiStatus
import com.example.rickandmortyguide.data.remote.RickApi
import kotlinx.coroutines.launch

const val TAG = "MainViewModel"

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val rickDb = getRickDb(application)
    private val repository = Repository(RickApi, rickDb)

    private val _loading = MutableLiveData<ApiStatus>()
    val loading: LiveData<ApiStatus> get() = _loading

    val characters = repository.characters
    val locations = repository.locations
    val episodes = repository.episodes

    private var _selectedCharacter = MutableLiveData<Character?>()
    val selectedCharacter: LiveData<Character?> get() = _selectedCharacter


    fun loadDatabases() {
        loadCharacters()
        loadLocations()
        loadEpisodes()
    }

    private fun loadCharacters() {
        viewModelScope.launch {
            _loading.value = ApiStatus.LOADINGCHARACTERS
            try {
                repository.loadCharacters()
                _loading.value = ApiStatus.CHARACTERSDONE
            } catch (e: Exception) {
                Log.e(TAG, "Error loading Characters $e")
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
                _loading.value = ApiStatus.LOCATIONSDONE
            } catch (e: Exception) {
                Log.e(TAG, "Error loading Locations $e")
                if (locations.value.isNullOrEmpty()) {
                    _loading.value = ApiStatus.ERROR
                } else {
                    _loading.value = ApiStatus.CHARACTERSDONE
                }
            }
        }
    }

    private fun loadEpisodes() {
        viewModelScope.launch {
            try {
                repository.loadEpisodes()
            } catch (e: Exception) {
                Log.e(TAG, "Error loading Episodes $e")
                if (episodes.value.isNullOrEmpty()) {
                    _loading.value = ApiStatus.ERROR
                } else {
                }
            }
        }
    }

    fun setSelectedCharacter(character: Character) {
        _selectedCharacter.value = character
    }

}