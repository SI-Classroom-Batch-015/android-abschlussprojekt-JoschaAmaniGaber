package com.example.rickandmortyguide.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.rickandmortyguide.data.local.RickDb
import com.example.rickandmortyguide.data.model.entities.Character
import com.example.rickandmortyguide.data.model.character.CharacterFromApi
import com.example.rickandmortyguide.data.model.character.CharacterResults
import com.example.rickandmortyguide.data.model.entities.Episode
import com.example.rickandmortyguide.data.model.entities.extractIdFromLocationUrl
import com.example.rickandmortyguide.data.model.results.EpisodeResults
import com.example.rickandmortyguide.data.model.info.InfoCharacter
import com.example.rickandmortyguide.data.model.info.InfoEpisode
import com.example.rickandmortyguide.data.model.info.InfoLocation
import com.example.rickandmortyguide.data.model.location.Location
import com.example.rickandmortyguide.data.model.location.LocationResults
import com.example.rickandmortyguide.data.remote.RickApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

private const val TAG = "Repository"

class Repository(
    private val service: RickApi,
    private val rickDb: RickDb,
) {
    private val _infoCharacter = MutableLiveData<InfoCharacter>()
    private val _infoLocation = MutableLiveData<InfoLocation>()
    private val _infoEpisode = MutableLiveData<InfoEpisode>()
    val infoCharacter: LiveData<InfoCharacter> get() = _infoCharacter
    val infoLocation: LiveData<InfoLocation> get() = _infoLocation
    val infoEpisode: LiveData<InfoEpisode> get() = _infoEpisode


    val characters: LiveData<List<Character>> get() = rickDb.dao.getAllCharacters()
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
                    _infoCharacter.postValue(resultCharacters.infoCharacter)
                    Log.d(TAG, "Loaded Characters Page: $page")
                } while (infoCharacter.value?.nextPage != null)
            }
        } catch (e: Exception) {
            Log.e(TAG, "Could not load characters at page $page: $e")
        }
    }

    suspend fun loadLocations() {

        var page = 0

        try {
            withContext(Dispatchers.IO) {
                var locationResults: LocationResults
                do {
                    locationResults = service.retrofitService.getAllLocations(++page)
                    rickDb.dao.insertAllLocations(locationResults.results)
                    _infoLocation.postValue(locationResults.infoLocation)
                    Log.d(TAG, "Loaded Locations Page: $page")
                } while (infoLocation.value?.nextPage != null)
            }
        } catch (e: Exception) {
            Log.e(TAG, "Could not load location page $page: $e")
        }
    }

    suspend fun loadEpisodes() {

        var page = 0

        try {
            withContext(Dispatchers.IO) {
                var episodeResults: EpisodeResults
                do {
                    episodeResults = service.retrofitService.getAllEpisodes(++page)
                    rickDb.dao.insertAllEpisodes(episodeResults.results)
                    _infoEpisode.postValue(episodeResults.infoEpisode)
                    Log.d(TAG, "Loaded Episodes Page: $page")
                } while (infoEpisode.value?.nextPage != null)
            }
        } catch (e: Exception) {
            Log.e(TAG, "Could not load location page $page: $e")
        }
    }

}