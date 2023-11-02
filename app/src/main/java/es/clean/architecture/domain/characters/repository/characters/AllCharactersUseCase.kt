package es.clean.architecture.domain.characters.repository.characters

import androidx.paging.Pager
import androidx.paging.PagingConfig
import es.clean.architecture.data.remote.characters.paging.CharactersPaging
import javax.inject.Inject

open class AllCharactersUseCase @Inject constructor(
    private val pagingSource: CharactersPaging
) {
    operator fun invoke(limit: Int) = Pager(
        // aquí el tamaño de página se comporta de forma curiosa,
        // la primera petición (es así) multiplica el pagesize * 3
        config = PagingConfig(
            pageSize = limit,
            prefetchDistance = limit / 2 // aquí recolectamos a la mitad, asi va más "fluido"
        ),
        pagingSourceFactory = {
            pagingSource
        }
    ).flow // Si nos fijamos creamos un flow directamente desde el caso de uso.
}