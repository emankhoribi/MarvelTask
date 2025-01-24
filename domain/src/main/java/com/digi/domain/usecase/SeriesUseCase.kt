package com.digi.domain.usecase

import com.digi.domain.entity.comicapi.ComicsResponse
import com.digi.domain.repository.CharacterDetailsRepo

class SeriesUseCase(private val comicsRepo: CharacterDetailsRepo) {

    suspend operator fun invoke(
        id: Int,
        apiKey: String,
        timeStamp: String,
        hash: String
    ): ComicsResponse = comicsRepo.getSeries(id, apiKey, timeStamp, hash)
}