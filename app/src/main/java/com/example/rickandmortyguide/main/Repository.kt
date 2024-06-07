package com.example.rickandmortyguide.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.rickandmortyguide.charcacter.model.Character
import com.example.rickandmortyguide.episode.model.Episode
import com.example.rickandmortyguide.location.model.Location
import com.example.rickandmortyguide.main.model.info.Info
import com.example.rickandmortyguide.main.local.character.RickDb
import com.example.rickandmortyguide.main.local.episode.EpisodeDb
import com.example.rickandmortyguide.main.local.location.LocationDb
import com.example.rickandmortyguide.main.remote.RickApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

private const val TAG = "Repository"
class Repository(
    private val service: RickApi,
    private val rickDb: RickDb,
    private val locationDb: LocationDb,
    private val episodeDb: EpisodeDb
) {
    private val _info = MutableLiveData<Info>()
    val info: LiveData<Info> get() = _info

    val characters: LiveData<List<Character>> get() = rickDb.dao.getAllCharacters()
    val locations: LiveData<List<Location>> get() = locationDb.dao.getAllLocations()
    val episodes: LiveData<List<Episode>> get() = episodeDb.dao.getAllEpisodes()

    suspend fun loadCharacters() {

        var page = 1

        try {
            withContext(Dispatchers.IO) {
                var resultCharacters = service.retrofitService.getCharacters()
                _info.postValue(resultCharacters.info)
                rickDb.dao.insertAllCharacters(resultCharacters.results)
                Log.d(TAG, "Loaded Characters Page: $page")
                do {
                    page++
                    resultCharacters = service.retrofitService.getCharactersPage(page)
                    rickDb.dao.insertAllCharacters(resultCharacters.results)
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
                locationDb.dao.insertAll(locationResults.results)
                _info.postValue(locationResults.info)
                Log.d(TAG, "Loaded Location Page: $page")
                do {
                    page++
                    locationResults = service.retrofitService.getAllLocations(page)
                    locationDb.dao.insertAll(locationResults.results)
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
                episodeDb.dao.insertAllEpisodes(episodeResults.results)
                _info.postValue(episodeResults.info)
                Log.d(TAG, "Loaded Episodes Page: $page")
                do {
                    page++
                    episodeResults = service.retrofitService.getAllEpisodes(page)
                    episodeDb.dao.insertAllEpisodes(episodeResults.results)
                    _info.postValue(episodeResults.info)
                    Log.d(TAG, "Loaded Episodes Page: $page")
                } while (info.value?.nextPage != null)
            }
        } catch (e: Exception) {
            Log.e(TAG, "Could not load location page $page: $e")
        }
    }

}