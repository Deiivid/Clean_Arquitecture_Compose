package es.clean.architecture.data.remote.episodes.interfaces

import es.clean.architecture.domain.episodes.models.RickyMortyEpisodesModel

interface EpisodesDataStore {
    suspend fun getAllEpisodes(page: Int): List<RickyMortyEpisodesModel.Episode>
}
