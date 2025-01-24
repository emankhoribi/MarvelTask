package com.digi.marveltask.di.module

import com.digi.domain.repository.CharactersRepo
import com.digi.domain.repository.CharacterDetailsRepo
import com.digi.domain.usecase.CharactersUseCase
import com.digi.domain.usecase.ComicsUseCase
import com.digi.domain.usecase.EventsUseCase
import com.digi.domain.usecase.SeriesUseCase
import com.digi.domain.usecase.StoriesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Provides
    fun provideCharactersUseCase(repo: CharactersRepo): CharactersUseCase =
        CharactersUseCase(repo)

    @Provides
    fun provideComicsUseCase(repo: CharacterDetailsRepo): ComicsUseCase =
        ComicsUseCase(repo)

    @Provides
    fun provideSeriesUseCase(repo: CharacterDetailsRepo): SeriesUseCase =
        SeriesUseCase(repo)

    @Provides
    fun provideStoriesUseCase(repo: CharacterDetailsRepo): StoriesUseCase =
        StoriesUseCase(repo)

    @Provides
    fun provideEventsUseCase(repo: CharacterDetailsRepo): EventsUseCase =
        EventsUseCase(repo)


}