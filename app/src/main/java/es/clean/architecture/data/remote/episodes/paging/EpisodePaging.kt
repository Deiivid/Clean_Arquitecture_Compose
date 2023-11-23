package es.clean.architecture.data.remote.episodes.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import es.clean.architecture.data.remote.episodes.repository.EpisodesRepository
import es.clean.architecture.domain.episodes.models.RickyMortyEpisodesModel
import javax.inject.Inject

open class EpisodePaging @Inject constructor(
    private val repository: EpisodesRepository
) : PagingSource<Int, RickyMortyEpisodesModel.Episode>() {
    override fun getRefreshKey(state: PagingState<Int, RickyMortyEpisodesModel.Episode>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, RickyMortyEpisodesModel.Episode> =
        try {
            val page = params.key ?: 1
            val response = repository.getAllEpisodes(
                page = page,
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
