package com.digi.marveltask.fragment.characters

import android.util.Log
import androidx.lifecycle.asLiveData
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.digi.domain.entity.Result
import com.digi.domain.usecase.CharactersUseCase
import com.digi.marveltask.util.Constants

class CharactersPaging(val nameStartsWith: String, val charactersUseCase: CharactersUseCase) :
    PagingSource<Int, Result>() {
    override fun getRefreshKey(state: PagingState<Int, Result>): Int? {
        return state.anchorPosition?.let {
            val anchorPage = state.closestPageToPosition(it)
            anchorPage?.prevKey?.plus(20) ?: anchorPage?.nextKey?.minus(20)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Result> {
        val page = params.key ?: 0

        return try {
            val data = charactersUseCase(
                page,
                nameStartsWith,
                Constants.API_KEY,
                Constants.TS,
                Constants.hash()
            )

            Log.d("TAG", "load: ${data.data}")
            LoadResult.Page(
                data = data.data.results,
                prevKey = if (page == 0) null else page - 20,
                nextKey = if (data.data.results?.isEmpty()!!) null else page + 20
            )
        } catch (e: Exception) {
            Log.d("TAG", "load: ${e.message}")
            LoadResult.Error(e)

        }
    }
}