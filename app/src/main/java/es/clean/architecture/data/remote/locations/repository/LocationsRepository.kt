package es.clean.architecture.data.remote.locations.repository

import es.clean.architecture.domain.locations.models.RickyMortyLocationsModel

interface LocationsRepository {
    suspend fun getAllLocations(page: Int): List<RickyMortyLocationsModel.Location>
}