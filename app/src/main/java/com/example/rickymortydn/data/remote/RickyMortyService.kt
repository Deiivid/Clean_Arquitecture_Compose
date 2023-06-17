package com.example.rickymortydn.data.remote

import com.example.rickymortydn.models.CharacterModel
import com.example.rickymortydn.models.common.constants.CHARACTERS
import retrofit2.http.GET

interface RickyMortyService {

    @GET(CHARACTERS)
    suspend fun getCharacters(): List<CharacterModel>
}