package es.clean.architecture.data.remote.locations.mapper

import es.clean.architecture.data.remote.locations.models.RemoteRickyMortyLocationsModel
import es.clean.architecture.domain.locations.models.RickyMortyLocationsModel

fun RemoteRickyMortyLocationsModel.RemoteLocation.toDomain(): RickyMortyLocationsModel.Location =
    RickyMortyLocationsModel.Location(
        created = this.created,
        dimension = this.dimension,
        id = this.id,
        name = this.name,
        residents = this.residents,
        type = this.type,
        url = this.url,
    )
