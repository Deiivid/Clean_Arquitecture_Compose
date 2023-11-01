package es.clean.architecture.data.remote.characters.datastore

import es.clean.architecture.data.remote.characters.interfaces.CharactersDataStore
import es.clean.architecture.data.remote.characters.mapper.toDomain
import es.clean.architecture.data.service.RickyMortyService
import es.clean.architecture.models.CharacterModel

internal class RemoteCharacterDataStoreImpl(
    private val rickyMortyService: RickyMortyService
) : CharactersDataStore {
    override suspend fun getAllCharacters(page: Int): List<CharacterModel.CharacterResult> {
        val result = this@RemoteCharacterDataStoreImpl.rickyMortyService.getAllCharacters(page)

        if (result.isSuccessful)
            return (result.body()?.results?.map { character ->
                character.toDomain()
            } ?: emptyList())

        return emptyList()
    }

}