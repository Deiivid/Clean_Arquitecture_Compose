package es.clean.architecture.data.remote.episodes.factory

import es.clean.architecture.data.remote.episodes.interfaces.EpisodesDataStore

class RickAndMortyEpisodeFactory(
    //val cache: EpisodesDataStore,
    val remote: EpisodesDataStore
)