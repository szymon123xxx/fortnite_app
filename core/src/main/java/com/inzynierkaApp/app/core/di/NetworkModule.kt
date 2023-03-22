package com.inzynierkaApp.app.core.di

import com.inzynierkaApp.app.core.BuildConfig
import com.inzynierkaApp.app.core.constants.Constants.Network.CONTENT_API
import com.inzynierkaApp.app.core.constants.Constants.Network.CONTENT_BASE_URL
import com.inzynierkaApp.app.core.constants.Constants.Timers.CONNECTION_TIMEOUT_MS
import com.inzynierkaApp.app.core.constants.Constants.Timers.READ_TIMEOUT_MS
import com.inzynierkaApp.app.data.api.ContentApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Singleton
    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply {
            level =
                if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
                else HttpLoggingInterceptor.Level.NONE
        }

    @Singleton
    @Provides
    fun provideDefaultOkHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .connectTimeout(CONNECTION_TIMEOUT_MS, TimeUnit.MILLISECONDS)
            .readTimeout(READ_TIMEOUT_MS, TimeUnit.MILLISECONDS)
            .build()

    @Singleton
    @Provides
    fun provideGsonConverterFactory(): GsonConverterFactory = GsonConverterFactory
        .create()


    @Named(CONTENT_API)
    @Singleton
    @Provides
    fun provideConfigApiRetrofit(
        client: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl(CONTENT_BASE_URL)
            .addConverterFactory(gsonConverterFactory)
            .client(client)
            .build()

    @Singleton
    @Provides
    fun provideContentApi(@Named(CONTENT_API) retrofit: Retrofit): ContentApi =
        retrofit.create(
            ContentApi::class.java
        )
}
