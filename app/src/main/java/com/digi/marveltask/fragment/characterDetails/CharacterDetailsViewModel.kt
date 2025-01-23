package com.digi.marveltask.fragment.characterDetails

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.digi.domain.entity.comicapi.ComicsResponse
import com.digi.domain.usecase.ComicsUseCase
import com.digi.marveltask.util.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterDetailsViewModel @Inject constructor(private val comicsUseCase: ComicsUseCase) :
    ViewModel() {

    private val _comicsFLow: MutableStateFlow<ComicsResponse?> = MutableStateFlow(null)
    val comicsFLow = _comicsFLow
    fun getComics(id: Int) {
        viewModelScope.launch {
            try {

                _comicsFLow.value =
                    comicsUseCase(id, Constants.API_KEY, Constants.TS, Constants.hash())


            } catch (e: Exception) {
                Log.e("Comics", e.message.toString())
            }
        }
    }
}