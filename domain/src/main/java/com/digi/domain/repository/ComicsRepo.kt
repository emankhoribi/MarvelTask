package com.digi.domain.repository

import com.digi.domain.entity.comicapi.ComicsResponse
import kotlinx.coroutines.flow.Flow

interface ComicsRepo {

     suspend fun getComics(id: Int, apiKey: String, timeStamp: String, hash: String): ComicsResponse
}