package com.example.rickymortydn.data.remote.characters.mapper

import com.example.rickymortydn.data.remote.characters.models.RemoteCharacterModel
import com.example.rickymortydn.data.remote.characters.models.RemoteLocation
import com.example.rickymortydn.data.remote.characters.models.RemoteOrigin
import com.example.rickymortydn.models.CharacterModel


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