package com.digi.domain.repository

import com.digi.domain.entity.comicapi.ComicsResponse

interface CharacterDetailsRepo {

     suspend fun getComics(id: Int, apiKey: String, timeStamp: String, hash: String): ComicsResponse
     suspend fun getSeries(id: Int, apiKey: String, timeStamp: String, hash: String): ComicsResponse
     suspend fun getStories(id: Int, apiKey: String, timeStamp: String, hash: String): ComicsResponse
     suspend fun getEvents(id: Int, apiKey: String, timeStamp: String, hash: String): ComicsResponse
}