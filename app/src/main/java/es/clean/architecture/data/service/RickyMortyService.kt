package es.clean.architecture.data.service


import es.clean.architecture.data.remote.characters.models.RemoteCharacterListModel
import es.clean.architecture.models.common.constants.CHARACTER
import retrofit2.Response
import retrofit2.http.GET

interface RickyMortyService {
    @GET(CHARACTER)
    suspend fun getCharacters(
    ): Response<RemoteCharacterListModel>
}
