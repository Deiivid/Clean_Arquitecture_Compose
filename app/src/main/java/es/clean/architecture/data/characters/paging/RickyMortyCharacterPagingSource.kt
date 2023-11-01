package es.clean.architecture.data.characters.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import es.clean.architecture.domain.characters.models.character.RickyMortyCharacter
import es.clean.architecture.domain.characters.repository.characters.CharacterRepository
import kotlinx.coroutines.flow.first
import javax.inject.Inject

open class RickyMortyCharacterPagingSource @Inject constructor(
    private val characterRepository: CharacterRepository,
) : PagingSource<Int, RickyMortyCharacter.Character>() {
    override fun getRefreshKey(state: PagingState<Int, RickyMortyCharacter.Character>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, RickyMortyCharacter.Character> {
        return try {
            val page = params.key ?: 0
            val response = characterRepository.getCharacters(
                page = page
            ).first()

            LoadResult.Page(
                data = response,
                prevKey = if (page == 0) null else page.minus(1),
                nextKey = if (response.isEmpty()) null else page.plus(1)
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}