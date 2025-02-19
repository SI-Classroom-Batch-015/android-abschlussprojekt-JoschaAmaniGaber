package com.example.rickandmortyguide.main.remote

import com.example.rickandmortyguide.charcacter.model.CharacterResults
import com.example.rickandmortyguide.episode.model.EpisodeResults
import com.example.rickandmortyguide.location.model.LocationResults
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


const val BASE_URL = "https://rickandmortyapi.com/api/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()
interface RickApiService {

    @GET("character/")
    suspend fun getCharactersPage(@Query("page") page:Int): CharacterResults

    @GET("location")
    suspend fun getLocations(): LocationResults

    @GET("location/")
    suspend fun getAllLocations(@Query("page") page:Int): LocationResults

    @GET("episode")
    suspend fun getEpisodes(): EpisodeResults

    @GET("episode/")
    suspend fun getAllEpisodes(@Query("page") page:Int): EpisodeResults

}

object RickApi {
    val retrofitService: RickApiService by lazy { retrofit.create(RickApiService::class.java) }
}