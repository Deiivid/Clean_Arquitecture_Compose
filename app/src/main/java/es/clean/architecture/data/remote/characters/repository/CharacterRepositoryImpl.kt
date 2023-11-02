package es.clean.architecture.data.remote.characters.repository

import es.clean.architecture.data.remote.characters.factory.RickAndMortyCharacterFactory
import es.clean.architecture.domain.characters.models.character.RickyMortyCharacterModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CharacterRepositoryImpl @Inject constructor(
    private val factory: RickAndMortyCharacterFactory
) : CharactersRepository {
    override suspend fun getAllCharacters(page: Int): List<RickyMortyCharacterModel.RickyMortyCharacter> =
        factory.remote.getAllCharacters(page)
}