package com.example.rickymortydn.data.api

import com.example.rickymortydn.models.common.constants.BASE_URL
import com.example.rickymortydn.data.remote.RickyMortyService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RickyMortyApiClient {
    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val apiService: RickyMortyService by lazy {
        retrofit.create(RickyMortyService::class.java)
    }
}