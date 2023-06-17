package com.example.rickymortydn.domain.characters.repository

import com.example.rickymortydn.models.CharacterModel

interface CharacterRepository {
    suspend fun getCharacters(): List<CharacterModel>

}