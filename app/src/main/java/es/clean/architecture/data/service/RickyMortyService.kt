package es.clean.architecture.data.service


import es.clean.architecture.data.common.CHARACTER
import es.clean.architecture.data.common.EPISODE
import es.clean.architecture.data.common.LOCATION
import es.clean.architecture.data.remote.characters.models.RemoteRickyMortyCharacterModel
import es.clean.architecture.data.remote.episodes.models.RemoteRickyMortyEpisodesModel
import es.clean.architecture.data.remote.locations.models.RemoteRickyMortyLocationsModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RickyMortyService {
    @GET(CHARACTER)
    suspend fun getAllCharacters(
        @Query("page") currentPage: Int, @Query("name") name: String? = null
    ): Response<RemoteRickyMortyCharacterModel>

    @GET(EPISODE)
    suspend fun getAllEpisodes(@Query("page") currentPage: Int): Response<RemoteRickyMortyEpisodesModel>

    @GET(LOCATION)
    suspend fun getAllLocations(@Query("page") currentPage: Int): Response<RemoteRickyMortyLocationsModel>

}
