package es.clean.architecture.data.remote.characters.datastore

import es.clean.architecture.data.remote.characters.mapper.toDomain
import es.clean.architecture.data.remote.characters.repository.CharactersRepository
import es.clean.architecture.data.service.RickyMortyService
import es.clean.architecture.domain.characters.models.character.RickyMortyCharacterModel

internal class RemoteCharacterDataStoreImpl(
    private val rickyMortyService: RickyMortyService
) : CharactersRepository {
    override suspend fun getAllCharacters(
        page: Int,
        name: String?
    ): List<RickyMortyCharacterModel.RickyMortyCharacter> {
        val result =
            this@RemoteCharacterDataStoreImpl.rickyMortyService.getAllCharacters(page, name)

        if (result.isSuccessful) {
            return (
                    result.body()?.results?.map { character ->
                        character.toDomain()
                    } ?: emptyList()
                    )
        }

        return emptyList()
    }
}
