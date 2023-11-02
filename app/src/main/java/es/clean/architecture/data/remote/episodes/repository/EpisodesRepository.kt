package es.clean.architecture.data.remote.episodes.repository

import es.clean.architecture.domain.characters.models.episodes.RickyMortyEpisodesModel

interface EpisodesRepository {
    suspend fun getAllEpisodes(page: Int): List<RickyMortyEpisodesModel.Episode>
}