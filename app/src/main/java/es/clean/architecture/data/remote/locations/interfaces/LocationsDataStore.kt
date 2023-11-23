package es.clean.architecture.data.remote.locations.interfaces

import es.clean.architecture.data.remote.locations.models.RemoteRickyMortyLocationsModel
import es.clean.architecture.domain.characters.models.character.RickyMortyCharacterModel


interface LocationsDataStore {
    suspend fun getAllLocations(page: Int): List<RemoteRickyMortyLocationsModel.RemoteLocation>
}
