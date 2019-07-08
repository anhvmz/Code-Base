package com.heligate.codebase.repository

import com.heligate.codebase.api.main.MainApiService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideMainRepository(mainApiService: MainApiService): MainRepository {
        return MainRepository(mainApiService)
    }
}