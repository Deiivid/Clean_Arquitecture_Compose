package com.example.rickymortydn.data.service


import com.example.rickymortydn.data.remote.characters.models.RemoteCharacterListModel
import com.example.rickymortydn.models.common.constants.CHARACTERS
import retrofit2.Response
import retrofit2.http.GET

interface RickyMortyService {
    @GET(CHARACTERS)
    suspend fun getCharacters(
    ): Response<RemoteCharacterListModel>
}
