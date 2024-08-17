package es.clean.architecture.data.remote.locations.datastore

import es.clean.architecture.data.remote.locations.mapper.toDomain
import es.clean.architecture.data.remote.locations.repository.LocationsRepository
import es.clean.architecture.data.service.RickyMortyService
import es.clean.architecture.domain.locations.models.RickyMortyLocationsModel

internal class RemoteLocationsDataStoreImpl(
    private val rickyMortyService: RickyMortyService
) : LocationsRepository {
    override suspend fun getAllLocations(page: Int): List<RickyMortyLocationsModel.Location> {
        val result = this@RemoteLocationsDataStoreImpl.rickyMortyService.getAllLocations(page)

        if (result.isSuccessful)
            return (result.body()?.results?.map { location ->
                location.toDomain()
            } ?: emptyList())

        return emptyList()
    }
}
