package es.clean.architecture.data.remote.characters.factory

import es.clean.architecture.data.remote.characters.interfaces.CharactersDataStore

class RickAndMortyCharacterFactory(
    val cache: CharactersDataStore,
    val remote: CharactersDataStore
)
