package com.heligate.codebase.repository

import com.heligate.codebase.api.ApiService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideStoreRepository(apiService: ApiService): MainRepository {
        return MainRepository(apiService)
    }
}