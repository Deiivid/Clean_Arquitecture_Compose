package es.clean.architecture.data.remote.episodes.mapper

import es.clean.architecture.data.remote.episodes.models.RemoteEpisodesModel
import es.clean.architecture.domain.characters.models.episodes.RickyMortyEpisodesModel


fun RemoteEpisodesModel.RemoteEpisode.toDomain(): RickyMortyEpisodesModel.Episode =
    RickyMortyEpisodesModel.Episode(
        airDate = this.created,
        characters = this.characters,
        created = this.created,
        episode = this.episode,
        id = this.id,
        name = this.name,
        url = this.url,

        )
