package com.digi.domain.usecase

import com.digi.domain.entity.comicapi.ComicsResponse
import com.digi.domain.repository.ComicsRepo
import kotlinx.coroutines.flow.Flow

class ComicsUseCase(private val comicsRepo: ComicsRepo) {

    suspend operator fun invoke(
        id: Int,
        apiKey: String,
        timeStamp: String,
        hash: String
    ): ComicsResponse = comicsRepo.getComics(id, apiKey, timeStamp, hash)
}