package es.clean.architecture.domain.characters

import androidx.paging.Pager
import androidx.paging.PagingConfig
import es.clean.architecture.data.characters.paging.RickyMortyCharacterPagingSource
import javax.inject.Inject

open class CharacterUseCase @Inject constructor(
    // private val characterRepository: CharacterRepository
    private val rickyMortyCharactersPaging: RickyMortyCharacterPagingSource

) {

    /*)suspend operator fun invoke(): Flow<List<RickyMortyCharacter.Character>> =
        characterRepository.getCharacters()
*/

    open operator fun invoke() = Pager(
        config = PagingConfig(
            pageSize = 10,
        ),
        pagingSourceFactory = { rickyMortyCharactersPaging }
    ).flow
}
