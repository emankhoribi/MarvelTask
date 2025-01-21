package com.digi.data.remote

import com.digi.domain.entity.CharactersResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServices {

    @GET("characters?limit=20")
    fun getCharacters(
        @Query("offset") offset: Int,
        @Query("nameStartsWith") nameStartsWith: String,
        @Query("apikey") apiKey: String
    ): Flow<CharactersResponse>
}