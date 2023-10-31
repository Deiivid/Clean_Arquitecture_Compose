package es.clean.architecture.data.remote.characters.mapper

import es.clean.architecture.data.remote.characters.models.RemoteCharacter
import es.clean.architecture.data.remote.characters.models.RemoteLocation
import es.clean.architecture.data.remote.characters.models.RemoteOrigin
import es.clean.architecture.models.RickyMortyCharacter


fun RemoteCharacter.toDomain(): RickyMortyCharacter.Character =
    RickyMortyCharacter.Character(
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

fun RemoteLocation.toDomain(): RickyMortyCharacter.Character.CharacterLocation =
    RickyMortyCharacter.Character.CharacterLocation(
        name = this.name,
        url = this.url
    )

fun RemoteOrigin.toDomain(): RickyMortyCharacter.Character.CharacterOrigin =
    RickyMortyCharacter.Character.CharacterOrigin(
        name = this.name,
        url = this.url
    )