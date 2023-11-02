package es.clean.architecture.data.remote.mapper

import es.clean.architecture.data.remote.characters.models.RemoteCharacter
import es.clean.architecture.data.remote.characters.models.RemoteLocation
import es.clean.architecture.data.remote.characters.models.RemoteOrigin
import es.clean.architecture.domain.characters.models.character.RickyMortyCharacterModel


fun RemoteCharacter.toDomain(): RickyMortyCharacterModel.RickyMortyCharacter =
    RickyMortyCharacterModel.RickyMortyCharacter(
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

fun RemoteLocation.toDomain(): RickyMortyCharacterModel.RickyMortyCharacter.CharacterLocation =
    RickyMortyCharacterModel.RickyMortyCharacter.CharacterLocation(
        name = this.name,
        url = this.url
    )

fun RemoteOrigin.toDomain(): RickyMortyCharacterModel.RickyMortyCharacter.CharacterOrigin =
    RickyMortyCharacterModel.RickyMortyCharacter.CharacterOrigin(
        name = this.name,
        url = this.url
    )