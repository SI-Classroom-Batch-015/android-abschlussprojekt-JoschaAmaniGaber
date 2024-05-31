package com.example.rickandmortyguide.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.rickandmortyguide.data.model.Character
import com.example.rickandmortyguide.data.model.Info
import com.example.rickandmortyguide.data.remote.RickApi

private const val TAG = "Repository"
class Repository(
    private val service: RickApi
) {
    private val _info = MutableLiveData<Info>()
    val info: LiveData<Info> get() = _info

    private val _characters = MutableLiveData<List<Character>>(listOf())
    val characters: LiveData<List<Character>> get() = _characters

    suspend fun loadCharacters() {
        try {
            val characters = service.retrofitService.getCharacters()
            _characters.postValue(characters.results)
            Log.d(TAG, "Loaded Characters")
        } catch (e: Exception) {
            Log.e(TAG, "Could not load characters: $e")
        }
    }
}