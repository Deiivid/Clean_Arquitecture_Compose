package com.example.rickymortydn.data.di


import com.example.rickymortydn.data.remote.characters.response.CharacterRepositoryImpl
import com.example.rickymortydn.data.service.RickyMortyService
import com.example.rickymortydn.domain.characters.repository.CharacterRepository
import com.example.rickymortydn.models.common.constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.math.BigInteger
import java.security.MessageDigest
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit(): RickyMortyService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(provideOkHttpClient())
            .build().create(RickyMortyService::class.java)
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun provideCharacterRepository(remoteService: RickyMortyService): CharacterRepository {
        return CharacterRepositoryImpl(remoteService)
    }

}