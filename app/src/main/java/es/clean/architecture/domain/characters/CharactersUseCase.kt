package es.clean.architecture.domain.characters

import es.clean.architecture.domain.characters.repository.CharacterRepository
import es.clean.architecture.models.RickyMortyCharacter
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CharacterUseCase @Inject constructor(
    private val characterRepository: CharacterRepository
) {
    suspend operator fun invoke(): Flow<List<RickyMortyCharacter.Character>> =
        characterRepository.getCharacters()
}