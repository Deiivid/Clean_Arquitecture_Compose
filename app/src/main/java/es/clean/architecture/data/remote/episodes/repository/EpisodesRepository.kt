package es.clean.architecture.data.remote.episodes.repository

import es.clean.architecture.domain.episodes.models.RickyMortyEpisodesModel

interface EpisodesRepository {
    suspend fun getAllEpisodes(page: Int): List<RickyMortyEpisodesModel.Episode>
}
