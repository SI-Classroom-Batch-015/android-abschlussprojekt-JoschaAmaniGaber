package com.example.rickandmortyguide.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.rickandmortyguide.data.local.CharacterDatabase
import com.example.rickandmortyguide.data.model.Character
import com.example.rickandmortyguide.data.model.Info
import com.example.rickandmortyguide.data.remote.ApiStatus
import com.example.rickandmortyguide.data.remote.RickApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

private const val TAG = "Repository"
class Repository(
    private val service: RickApi,
    private val database: CharacterDatabase
) {
    private val _info = MutableLiveData<Info>()
    val info: LiveData<Info> get() = _info

    val characters: LiveData<List<Character>> get() = database.dao.getAll()

    private val _selectedCharacter = MutableLiveData<Character>()
    val selectedCharacter: LiveData<Character> get() = _selectedCharacter

    suspend fun getCharacters() {
        try {
            ApiStatus.LOADING
            withContext(Dispatchers.IO) {
                val characters = service.retrofitService.getCharacters().results
                database.dao.insertAll(characters)
                ApiStatus.DONE
            }
        } catch (e: Exception) {
            ApiStatus.ERROR
        }
    }

    suspend fun getCharacterById(id: Int) {
        try {
            withContext(Dispatchers.IO) {
                val character = service.retrofitService.getCharacterById(id)
                _selectedCharacter.postValue(character)
                Log.e(TAG, "Loaded Character")
            }
        } catch (e: Exception) {
            Log.e(TAG, "Could not load characterById: $e from A")
        }
    }

    suspend fun getCharacter(idCharacter: Int) {
        database.dao.getCharacterById(idCharacter)
    }
}