package es.clean.architecture.data.remote.characters.response

import es.clean.architecture.data.remote.characters.mapper.toDomain
import es.clean.architecture.data.service.RickyMortyService
import es.clean.architecture.domain.characters.repository.CharacterRepository
import es.clean.architecture.models.RickyMortyCharacter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CharacterRepositoryImpl @Inject constructor(
    private val apiService: RickyMortyService
) : CharacterRepository {

    override suspend fun getCharacters(): Flow<List<RickyMortyCharacter.Character>> = flow {
        val characters = apiService.getCharacters()

        if (characters.isSuccessful) {
            val results = characters.body()?.results
            if (!results.isNullOrEmpty()) {
                val mappedResults = results.map { charactersMapped ->
                    charactersMapped.toDomain()
                }
                emit(mappedResults)
            } else {
                emit(emptyList())
            }
        } else {
            emit(emptyList())
        }


    }
}