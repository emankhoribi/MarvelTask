package com.digi.domain.usecase

import com.digi.domain.entity.comicapi.ComicsResponse
import com.digi.domain.repository.CharacterDetailsRepo

class ComicsUseCase(private val comicsRepo: CharacterDetailsRepo) {

    suspend operator fun invoke(
        id: Int,
        apiKey: String,
        timeStamp: String,
        hash: String
    ): ComicsResponse = comicsRepo.getComics(id, apiKey, timeStamp, hash)
}