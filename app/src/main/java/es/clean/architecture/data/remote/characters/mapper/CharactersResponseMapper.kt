package es.clean.architecture.data.remote.characters.mapper

import es.clean.architecture.data.remote.characters.models.RemoteCharacterModel
import es.clean.architecture.data.remote.characters.models.RemoteLocation
import es.clean.architecture.data.remote.characters.models.RemoteOrigin
import es.clean.architecture.models.CharacterModel


fun RemoteCharacterModel.toDomain(): CharacterModel.CharacterResult =
    CharacterModel.CharacterResult(
        created = this.created,
        episode = this.episode,
        gender = this.gender,
        id = this.id,
        image = this.image,
        characterLocation = this.remoteLocation.toDomain(),
        name = this.name,
        characterOrigin = this.remoteOrigin.toDomain(),
        species = this.species,
        status = this.status,
        type = this.type

    )

fun RemoteLocation.toDomain(): CharacterModel.CharacterResult.CharacterLocation =
    CharacterModel.CharacterResult.CharacterLocation(
        name = this.name,
        url = this.url
    )

fun RemoteOrigin.toDomain(): CharacterModel.CharacterResult.CharacterOrigin =
    CharacterModel.CharacterResult.CharacterOrigin(
        name = this.name,
        url = this.url
    )