package es.clean.architecture.data.remote.locations.interfaces

import es.clean.architecture.data.remote.locations.models.RemoteRickyMortyLocationsModel

interface LocationsDataStore {
    suspend fun getAllLocations(page: Int): List<RemoteRickyMortyLocationsModel.RemoteLocation>
}
