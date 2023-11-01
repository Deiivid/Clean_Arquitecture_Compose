package es.clean.architecture.data.remote.characters.repository

import es.clean.architecture.data.remote.characters.factory.RickAndMortyCharacterFactory
import es.clean.architecture.models.CharacterModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CharacterRepositoryImpl @Inject constructor(
    private val factory: RickAndMortyCharacterFactory
) : CharactersRepository {
    override suspend fun getAllCharacters(page: Int): List<CharacterModel.CharacterResult> =
        factory.remote.getAllCharacters(page)
}