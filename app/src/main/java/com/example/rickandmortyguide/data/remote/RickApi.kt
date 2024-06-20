package com.example.rickandmortyguide.data.remote

import com.example.rickandmortyguide.data.model.character.CharacterResults
import com.example.rickandmortyguide.data.model.results.EpisodeResults
import com.example.rickandmortyguide.data.model.location.LocationResults
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

    @GET("location/")
    suspend fun getAllLocations(@Query("page") page:Int): LocationResults

    @GET("episode/")
    suspend fun getAllEpisodes(@Query("page") page:Int): EpisodeResults

}

object RickApi {
    val retrofitService: RickApiService by lazy { retrofit.create(RickApiService::class.java) }
}