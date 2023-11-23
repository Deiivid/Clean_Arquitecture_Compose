package es.clean.architecture.data.remote.locations.factory

import es.clean.architecture.data.remote.locations.interfaces.LocationsDataStore

class RickAndMortyLocationFactory(
    val cache: LocationsDataStore,
    val remote: LocationsDataStore
)
