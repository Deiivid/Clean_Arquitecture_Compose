package com.example.rickymortydn.domain.characters.repository

import com.example.rickymortydn.models.CharacterModel
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {
    suspend fun getCharacters(): Flow<List<CharacterModel.CharacterResult>>
}