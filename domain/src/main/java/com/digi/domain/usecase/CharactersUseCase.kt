package com.digi.domain.usecase

import com.digi.domain.entity.CharactersResponse
import com.digi.domain.repository.CharactersRepo

class CharactersUseCase(private val charactersRepo: CharactersRepo) {
    suspend operator fun invoke(
        offset: Int,
        nameStartsWith: String,
        apiKey: String,
        timeStamp: String,
        hash: String
    ): CharactersResponse {
        return if (nameStartsWith.isEmpty())
            charactersRepo.getCharacters(offset, apiKey, timeStamp, hash)
        else
            charactersRepo.getSearchCharacters(offset, nameStartsWith, apiKey, timeStamp, hash)
    }


}