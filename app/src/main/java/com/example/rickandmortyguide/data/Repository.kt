package com.example.rickandmortyguide.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.rickandmortyguide.data.local.CharacterDatabase
import com.example.rickandmortyguide.data.model.Character
import com.example.rickandmortyguide.data.model.Info
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

    var page = 2

    suspend fun loadCharacters() {
        try {
            withContext(Dispatchers.IO) {
                val resultCharacters = service.retrofitService.getCharacters()
                _info.postValue(resultCharacters.info)
                database.dao.insertAll(resultCharacters.results)
                Log.d(TAG, "Loaded Characters")
            }
        } catch (e: Exception) {
            Log.e(TAG, "Could not load characters: $e")
        }
    }

    suspend fun loadCharactersPage() {
        do withContext(Dispatchers.IO) {
            val resultCharactersPage = service.retrofitService.getCharactersPage(page)
            database.dao.insertAll(resultCharactersPage.results)
            _info.postValue(resultCharactersPage.info)
            page++
        }
        while (info.value?.nextPage != null)
    }


}