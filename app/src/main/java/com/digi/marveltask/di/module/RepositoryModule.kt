package com.digi.marveltask.di.module

import com.digi.data.remote.ApiServices
import com.digi.data.repository.CharactersRepoImpl
import com.digi.data.repository.CharacterDetailsRepoImpl
import com.digi.domain.repository.CharactersRepo
import com.digi.domain.repository.CharacterDetailsRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    fun provideCharactersRepo(apiServices: ApiServices): CharactersRepo =
        CharactersRepoImpl(apiServices)

    @Provides
    fun provideCharacterDetailsRepo(apiServices: ApiServices): CharacterDetailsRepo =
        CharacterDetailsRepoImpl(apiServices)




}