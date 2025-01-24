package com.digi.marveltask.fragment.characterDetails

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.digi.domain.entity.comicapi.ComicsResponse
import com.digi.domain.usecase.ComicsUseCase
import com.digi.domain.usecase.EventsUseCase
import com.digi.domain.usecase.SeriesUseCase
import com.digi.domain.usecase.StoriesUseCase
import com.digi.marveltask.util.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterDetailsViewModel @Inject constructor(private val comicsUseCase: ComicsUseCase,
    private val seriesUseCase: SeriesUseCase, private val eventsUseCase: EventsUseCase,
    private val storiesUseCase: StoriesUseCase) :
    ViewModel() {

    private val _comicsFLow: MutableStateFlow<ComicsResponse?> = MutableStateFlow(null)
    val comicsFLow = _comicsFLow

    private val _seriesFLow: MutableStateFlow<ComicsResponse?> = MutableStateFlow(null)
    val seriesFlow = _seriesFLow

    private val _eventsFLow: MutableStateFlow<ComicsResponse?> = MutableStateFlow(null)
    val eventsFLow = _eventsFLow

    private val _storiesFLow: MutableStateFlow<ComicsResponse?> = MutableStateFlow(null)
    val storiesFLow = _storiesFLow

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

    fun getEvents(id: Int) {
        viewModelScope.launch {
            try {

                _eventsFLow.value =
                    eventsUseCase(id, Constants.API_KEY, Constants.TS, Constants.hash())


            } catch (e: Exception) {
                Log.e("Events", e.message.toString())
            }
        }
    }

    fun getSeries(id: Int) {
        viewModelScope.launch {
            try {

                _seriesFLow.value =
                    seriesUseCase(id, Constants.API_KEY, Constants.TS, Constants.hash())


            } catch (e: Exception) {
                Log.e("Series", e.message.toString())
            }
        }
    }

    fun getStories(id: Int) {
        viewModelScope.launch {
            try {

                _storiesFLow.value =
                    storiesUseCase(id, Constants.API_KEY, Constants.TS, Constants.hash())


            } catch (e: Exception) {
                Log.e("Stories", e.message.toString())
            }
        }
    }
}