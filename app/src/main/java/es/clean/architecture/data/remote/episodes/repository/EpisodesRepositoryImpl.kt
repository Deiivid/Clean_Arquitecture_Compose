package es.clean.architecture.data.remote.episodes.repository

import es.clean.architecture.data.remote.episodes.factory.RickAndMortyEpisodeFactory
import es.clean.architecture.domain.episodes.models.RickyMortyEpisodesModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class EpisodesRepositoryImpl @Inject constructor(
    private val factory: RickAndMortyEpisodeFactory
) : EpisodesRepository {
    override suspend fun getAllEpisodes(page: Int): List<RickyMortyEpisodesModel.Episode> =
        factory.remote.getAllEpisodes(page)

}
