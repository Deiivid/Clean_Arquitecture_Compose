package es.clean.architecture.data.remote.characters.interfaces

import es.clean.architecture.domain.characters.models.character.RickyMortyCharacterModel

interface CharactersDataStore {
    suspend fun getAllCharacters(page: Int): List<RickyMortyCharacterModel.RickyMortyCharacter>
}
