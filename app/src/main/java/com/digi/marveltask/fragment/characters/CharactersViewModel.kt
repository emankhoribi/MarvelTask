package com.digi.marveltask.fragment.characters

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.digi.domain.entity.Result
import com.digi.domain.usecase.CharactersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


@HiltViewModel
class CharactersViewModel @Inject constructor(val charactersUseCase: CharactersUseCase): ViewModel() {

    fun getCharacterList(): Flow<PagingData<Result>> {
        return Pager (config = PagingConfig(pageSize = 20, maxSize = 200),
            pagingSourceFactory = {CharactersPaging("", charactersUseCase)}).flow.cachedIn(viewModelScope)
    }

    fun getSearchedCharacterList(nameStartsWith: String): Flow<PagingData<Result>> {
        return Pager (config = PagingConfig(pageSize = 20, maxSize = 200),
            pagingSourceFactory = {CharactersPaging(nameStartsWith, charactersUseCase)}).flow.cachedIn(viewModelScope)
    }
}