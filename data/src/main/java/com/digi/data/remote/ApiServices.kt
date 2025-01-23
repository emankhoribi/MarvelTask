package com.digi.data.remote

import com.digi.domain.entity.CharactersResponse
import com.digi.domain.entity.comicapi.ComicsResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiServices {

    @GET("characters?limit=20")
    suspend fun getCharacters(
        @Query("offset") offset: Int,
        @Query("apikey") apiKey: String,
        @Query("ts") timeStamp: String,
        @Query("hash") hash: String
    ): CharactersResponse

    @GET("characters?limit=20")
    suspend fun getSearchCharacters(
        @Query("offset") offset: Int,
        @Query("nameStartsWith") nameStartsWith: String,
        @Query("apikey") apiKey: String,
        @Query("ts") timeStamp: String,
        @Query("hash") hash: String
    ): CharactersResponse

    @GET("characters/{id}/comics")
    suspend fun getCharacterComics(
        @Path("id") id: Int,
        @Query("apikey") apiKey: String,
        @Query("ts") timeStamp: String,
        @Query("hash") hash: String
    ): ComicsResponse
}