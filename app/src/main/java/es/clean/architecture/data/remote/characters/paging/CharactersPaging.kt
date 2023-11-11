package es.clean.architecture.data.remote.characters.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import es.clean.architecture.data.remote.characters.repository.CharactersRepository
import es.clean.architecture.domain.characters.models.character.RickyMortyCharacterModel
import javax.inject.Inject

open class CharactersPaging @Inject constructor(
    private val repository: CharactersRepository,
    private val query: String? // Aceptar el parámetro query

) : PagingSource<Int, RickyMortyCharacterModel.RickyMortyCharacter>() {
    // Este método es as is, no se toca salvo lo estrictamente necesario
    // (obtención de la clave de páginación) :D
    override fun getRefreshKey(state: PagingState<Int, RickyMortyCharacterModel.RickyMortyCharacter>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    // Aquí sí hay que tocar un poquito...
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, RickyMortyCharacterModel.RickyMortyCharacter> =
        try {
            val page = params.key ?: 1
            val limit = params.loadSize
            //Timber.tag("Paging").i("Page: $page")
            // Puede ser un flow, o una suspend que se traiga cosas de cualquier sitio,
            // es un repositorio, por tanto...
            val response = repository.getAllCharacters(
                page = page,
                name = query
            )

            LoadResult.Page(
                data = response,
                prevKey = if (page == 1) null else page.minus(1),
                nextKey = if (response.isEmpty()) null else page.plus(1),
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
}