package com.digi.marveltask.di.module

import com.digi.domain.repository.CharactersRepo
import com.digi.domain.repository.ComicsRepo
import com.digi.domain.usecase.CharactersUseCase
import com.digi.domain.usecase.ComicsUseCase
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
    fun provideComicsUseCase(repo: ComicsRepo): ComicsUseCase =
        ComicsUseCase(repo)


}