package com.example.rickymortydn.data.remote.characters.response

import com.example.rickymortydn.data.remote.characters.mapper.toDomain
import com.example.rickymortydn.data.service.RickyMortyService
import com.example.rickymortydn.domain.characters.repository.CharacterRepository
import com.example.rickymortydn.models.CharacterModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CharacterRepositoryImpl @Inject constructor(
    private val apiService: RickyMortyService
) : CharacterRepository {

    override suspend fun getCharacters(): Flow<List<CharacterModel.CharacterResult>> = flow {
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