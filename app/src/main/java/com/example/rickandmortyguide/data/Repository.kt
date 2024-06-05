package com.example.rickandmortyguide.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.rickandmortyguide.data.local.CharacterDb
import com.example.rickandmortyguide.data.local.LocationDb
import com.example.rickandmortyguide.data.model.Character
import com.example.rickandmortyguide.data.model.Info
import com.example.rickandmortyguide.data.model.Location
import com.example.rickandmortyguide.data.remote.RickApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

private const val TAG = "Repository"

class Repository(
    private val service: RickApi,
    private val characterDb: CharacterDb,
    private val locationDb: LocationDb
) {
    private val _info = MutableLiveData<Info>()
    val info: LiveData<Info> get() = _info

    val characters: LiveData<List<Character>> get() = characterDb.dao.getAllCharacters()
    val locations: LiveData<List<Location>> get() = locationDb.dao.getAllLocations()

    suspend fun loadCharacters() {

        try {
            withContext(Dispatchers.IO) {
                val resultCharacters = service.retrofitService.getCharacters()
                _info.postValue(resultCharacters.info)
                characterDb.dao.insertAllCharacters(resultCharacters.results)
                Log.d(TAG, "Loaded Characters Page: 1")
            }
        } catch (e: Exception) {
            Log.e(TAG, "Could not load characters: $e")
        }
    }

    suspend fun loadCharactersPage() {

        var page = 1

        do withContext(Dispatchers.IO) {
            try {
                page++
                val resultCharactersPage = service.retrofitService.getCharactersPage(page)
                characterDb.dao.insertAllCharacters(resultCharactersPage.results)
                _info.postValue(resultCharactersPage.info)
                Log.d(TAG, "Loaded Characters Page: $page")
            } catch (e: Exception) {
                Log.e(TAG, "Could not load character page $page $e")
            }
        } while (info.value?.nextPage != null)
    }

    suspend fun loadLocations() {

        try {
            withContext(Dispatchers.IO) {
                val locationResults = service.retrofitService.getLocations()
                locationDb.dao.insertAll(locationResults.results)
                _info.postValue(locationResults.info)
                Log.d(TAG, "Loaded Location Page: 1")
            }
        } catch (e: Exception) {
            Log.e(TAG, "Could not load location: $e")
        }
    }

    suspend fun loadLocationsPage() {

        var page = 1

        do withContext(Dispatchers.IO) {
            try {
                page++
                val allLocationResults = service.retrofitService.getAllLocations(page)
                locationDb.dao.insertAll(allLocationResults.results)
                _info.postValue(allLocationResults.info)
                Log.d(TAG, "Loaded Locations Page: $page")
            } catch (e: Exception) {
                Log.e(TAG, "Could not load location page $page $e")
            }
        } while (info.value?.nextPage != null)
    }
}