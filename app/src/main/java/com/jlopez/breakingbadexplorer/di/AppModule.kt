package com.jlopez.breakingbadexplorer.di

import com.jlopez.breakingbadexplorer.data.remote.BreakingBadApi
import com.jlopez.breakingbadexplorer.repository.CharacterRepository
import com.jlopez.breakingbadexplorer.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideCharacterRepository(
        api: BreakingBadApi
    ) = CharacterRepository(api)

    @Singleton
    @Provides
    fun provideBreakingBadApi(): BreakingBadApi {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(BreakingBadApi::class.java)
    }
}