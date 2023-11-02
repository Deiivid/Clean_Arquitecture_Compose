package es.clean.architecture.data.remote.episodes.interfaces

import es.clean.architecture.domain.characters.models.episodes.RickyMortyEpisodesModel


interface EpisodesDataStore {
    suspend fun getAllEpisodes(page: Int): List<RickyMortyEpisodesModel.Episode>
}