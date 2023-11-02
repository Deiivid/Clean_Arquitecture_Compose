package es.clean.architecture.data.remote.episodes.datastore

import es.clean.architecture.data.remote.episodes.mapper.toDomain
import es.clean.architecture.data.remote.episodes.repository.EpisodesRepository
import es.clean.architecture.data.service.RickyMortyService
import es.clean.architecture.domain.characters.models.episodes.RickyMortyEpisodesModel

internal class RemoteEpisodesDataStoreImpl(
    private val rickyMortyService: RickyMortyService
) : EpisodesRepository {
    override suspend fun getAllEpisodes(page: Int): List<RickyMortyEpisodesModel.Episode> {
        val result = this@RemoteEpisodesDataStoreImpl.rickyMortyService.getAllEpisodes(page)

        if (result.isSuccessful)
            return (result.body()?.results?.map { character ->
                character.toDomain()
            } ?: emptyList())

        return emptyList()
    }

}