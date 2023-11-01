package es.clean.architecture.data.service


import es.clean.architecture.data.characters.remote.models.RemoteRickyMortyCharacter
import es.clean.architecture.data.common.CHARACTER
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RickyMortyService {
    @GET(CHARACTER)
    suspend fun getCharacters(
        @Query("page") page: Int,
        ): Response<RemoteRickyMortyCharacter>
}
