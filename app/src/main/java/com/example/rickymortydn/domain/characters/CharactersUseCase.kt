package com.example.rickymortydn.domain.characters

import com.example.rickymortydn.domain.characters.repository.CharacterRepository
import com.example.rickymortydn.models.CharacterModel

class CharacterUseCase(private val characterRepository: CharacterRepository) {

    suspend fun getCharacters(): List<CharacterModel> {
        return characterRepository.getCharacters()
    }
}