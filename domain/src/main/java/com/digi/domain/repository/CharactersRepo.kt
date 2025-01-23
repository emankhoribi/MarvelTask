package com.digi.domain.repository

import com.digi.domain.entity.CharactersResponse
import kotlinx.coroutines.flow.Flow

interface CharactersRepo {

    suspend fun getCharacters(offset: Int, apiKey: String, timeStamp: String, hash: String ): CharactersResponse
    suspend fun getSearchCharacters(offset: Int, nameStartsWith: String, apiKey: String, timeStamp: String, hash: String): CharactersResponse
}