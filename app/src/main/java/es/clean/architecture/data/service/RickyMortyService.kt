package es.clean.architecture.data.service


import es.clean.architecture.data.common.CHARACTER
import es.clean.architecture.data.common.EPISODE
import es.clean.architecture.data.remote.characters.models.RemoteRickyMortyCharacterModel
import es.clean.architecture.data.remote.episodes.models.RemoteEpisodesModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RickyMortyService {
    @GET(CHARACTER)
    suspend fun getAllCharacters(@Query("page") currentPage: Int): Response<RemoteRickyMortyCharacterModel>

    @GET(EPISODE)
    suspend fun getAllEpisodes(@Query("page") currentPage: Int): Response<RemoteEpisodesModel>

}
