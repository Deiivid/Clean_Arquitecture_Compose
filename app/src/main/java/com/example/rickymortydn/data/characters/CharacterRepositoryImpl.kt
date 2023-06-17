package com.example.rickymortydn.data.characters

import com.example.rickymortydn.data.remote.RickyMortyService
import com.example.rickymortydn.domain.characters.repository.CharacterRepository
import com.example.rickymortydn.models.CharacterModel

class CharacterRepositoryImpl(private val apiService: RickyMortyService) : CharacterRepository {
    override suspend fun getCharacters(): List<CharacterModel> {
        return apiService.getCharacters()
    }
}