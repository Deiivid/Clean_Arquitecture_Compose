package com.example.rickymortydn.domain.characters

import com.example.rickymortydn.domain.characters.repository.CharacterRepository
import com.example.rickymortydn.models.CharacterModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CharacterUseCase @Inject constructor(
    private val characterRepository: CharacterRepository
) {
    suspend operator fun invoke(): Flow<List<CharacterModel.CharacterResult>> =
        characterRepository.getCharacters()
}