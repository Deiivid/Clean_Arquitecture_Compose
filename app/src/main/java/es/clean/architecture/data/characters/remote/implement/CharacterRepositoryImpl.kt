package es.clean.architecture.data.characters.remote.implement

import es.clean.architecture.data.characters.mapper.toDomain
import es.clean.architecture.data.service.RickyMortyService
import es.clean.architecture.domain.characters.repository.characters.CharacterRepository
import es.clean.architecture.domain.characters.models.character.RickyMortyCharacter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CharacterRepositoryImpl @Inject constructor(
    private val remoteImplService: RickyMortyService
) : CharacterRepository {

    override suspend fun getCharacters(
        page:Int
    ): Flow<List<RickyMortyCharacter.Character>> = flow {
        val characters = remoteImplService.getCharacters(
            page=page
        )

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