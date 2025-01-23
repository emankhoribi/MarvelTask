package com.digi.data.repository

import com.digi.data.remote.ApiServices
import com.digi.domain.entity.comicapi.ComicsResponse
import com.digi.domain.repository.ComicsRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class ComicsRepoImpl(private val apiServices: ApiServices) : ComicsRepo {
    override suspend fun getComics(
        id: Int,
        apiKey: String,
        timeStamp: String,
        hash: String
    ):ComicsResponse {
       return apiServices.getCharacterComics(id, apiKey, timeStamp, hash)
    }
}