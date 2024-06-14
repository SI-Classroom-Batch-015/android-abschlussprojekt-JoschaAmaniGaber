package com.example.rickandmortyguide.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.rickandmortyguide.charcacter.model.Character
import com.example.rickandmortyguide.charcacter.model.CharacterLocationCrossRef
import com.example.rickandmortyguide.charcacter.model.CharacterResults
import com.example.rickandmortyguide.charcacter.model.CharacterWithLocations
import com.example.rickandmortyguide.charcacter.model.extractIdFromLocationUrl
import com.example.rickandmortyguide.episode.model.Episode
import com.example.rickandmortyguide.location.model.Location
import com.example.rickandmortyguide.main.model.info.Info
import com.example.rickandmortyguide.main.local.RickDb
import com.example.rickandmortyguide.main.remote.RickApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

private const val TAG = "Repository"
class Repository(
    private val service: RickApi,
    private val rickDb: RickDb,
) {
    private val _info = MutableLiveData<Info>()
    val info: LiveData<Info> get() = _info

    val characters: LiveData<List<CharacterWithLocations>> get() = rickDb.dao.getAllCharacters()
    val locations: LiveData<List<Location>> get() = rickDb.dao.getAllLocations()
    val episodes: LiveData<List<Episode>> get() = rickDb.dao.getAllEpisodes()

    suspend fun loadCharacters() {

        var page = 0

        try {
            withContext(Dispatchers.IO) {
                var resultCharacters: CharacterResults
                do {
                    resultCharacters = service.retrofitService.getCharactersPage(++page)
                    rickDb.dao.insertAllCharacters(resultCharacters.results)
                    resultCharacters.results.forEach { character ->
                        val locations = emptyList<String>() //it.location
                        locations.forEach {
                            val locationId = extractIdFromLocationUrl(it)
                            val crossRef = CharacterLocationCrossRef(character.id, locationId)
                            rickDb.dao.insertCharacterLocationCrossRef(crossRef)
                        }
                    }

                    _info.postValue(resultCharacters.info)
                    Log.d(TAG, "Loaded Characters Page: $page")
                }  while (info.value?.nextPage != null)
            }
        } catch (e: Exception) {
            Log.e(TAG, "Could not load characters at page $page: $e")
        }
    }

    suspend fun loadLocations() {

        var page = 1

        try {
            withContext(Dispatchers.IO) {
                var locationResults = service.retrofitService.getLocations()
                rickDb.dao.insertAll(locationResults.results)
                _info.postValue(locationResults.info)
                Log.d(TAG, "Loaded Location Page: $page")
                do {
                    page++
                    locationResults = service.retrofitService.getAllLocations(page)
                    rickDb.dao.insertAll(locationResults.results)
                    _info.postValue(locationResults.info)
                    Log.d(TAG, "Loaded Locations Page: $page")
                }  while (info.value?.nextPage != null)
            }
        } catch (e: Exception) {
            Log.e(TAG, "Could not load location page $page: $e")
        }
    }

    suspend fun loadEpisodes() {

        var page = 1

        try {
            withContext(Dispatchers.IO) {
                var episodeResults = service.retrofitService.getEpisodes()
                rickDb.dao.insertAllEpisodes(episodeResults.results)
                _info.postValue(episodeResults.info)
                Log.d(TAG, "Loaded Episodes Page: $page")
                do {
                    page++
                    episodeResults = service.retrofitService.getAllEpisodes(page)
                    rickDb.dao.insertAllEpisodes(episodeResults.results)
                    _info.postValue(episodeResults.info)
                    Log.d(TAG, "Loaded Episodes Page: $page")
                } while (info.value?.nextPage != null)
            }
        } catch (e: Exception) {
            Log.e(TAG, "Could not load location page $page: $e")
        }
    }

}