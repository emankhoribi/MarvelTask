package com.digi.data.repository

import android.provider.SyncStateContract.Constants
import com.digi.data.remote.ApiServices
import com.digi.domain.entity.CharactersResponse
import com.digi.domain.repository.CharactersRepo
import kotlinx.coroutines.flow.Flow

class CharactersRepoImpl(private val apiServices: ApiServices): CharactersRepo {
    override suspend fun getCharacters(
        offset: Int,
        apiKey: String,
        timeStamp: String,
        hash: String
    ): CharactersResponse {
        return apiServices.getCharacters(
            offset = offset,
           apiKey = apiKey,
            timeStamp = timeStamp,
            hash = hash)
    }

    override suspend fun getSearchCharacters(
        offset: Int,
        nameStartsWith: String,
        apiKey: String,
        timeStamp: String,
        hash: String
    ): CharactersResponse {
        return apiServices.getSearchCharacters(
            offset = offset,
            nameStartsWith = nameStartsWith,
            apiKey = apiKey,
            timeStamp = timeStamp,
            hash = hash)
    }
}