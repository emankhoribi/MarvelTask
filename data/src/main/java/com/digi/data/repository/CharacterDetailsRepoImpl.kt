package com.digi.data.repository

import com.digi.data.remote.ApiServices
import com.digi.domain.entity.comicapi.ComicsResponse
import com.digi.domain.repository.CharacterDetailsRepo

class CharacterDetailsRepoImpl(private val apiServices: ApiServices) : CharacterDetailsRepo {
    override suspend fun getComics(
        id: Int,
        apiKey: String,
        timeStamp: String,
        hash: String
    ):ComicsResponse = apiServices.getCharacterComics(id, apiKey, timeStamp, hash)

    override suspend fun getSeries(
        id: Int,
        apiKey: String,
        timeStamp: String,
        hash: String
    ): ComicsResponse = apiServices.getCharacterSeries(id, apiKey, timeStamp, hash)

    override suspend fun getStories(
        id: Int,
        apiKey: String,
        timeStamp: String,
        hash: String
    ): ComicsResponse = apiServices.getCharacterStories(id, apiKey, timeStamp, hash)

    override suspend fun getEvents(
        id: Int,
        apiKey: String,
        timeStamp: String,
        hash: String
    ): ComicsResponse = apiServices.getCharacterEvents(id, apiKey, timeStamp, hash)
}