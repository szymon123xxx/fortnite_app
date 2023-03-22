package com.inzynierkaApp.app.core.di

import com.inzynierkaApp.app.data.api.ContentApi
import com.inzynierkaApp.app.data.repositories.ContentRepositoryImpl
import com.inzynierkaApp.app.domain.repositories.ContentRepository
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Singleton
    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Singleton
    @Provides
    fun provideContentRepository(
        api: ContentApi
    ): ContentRepository =
        ContentRepositoryImpl(api)
}
