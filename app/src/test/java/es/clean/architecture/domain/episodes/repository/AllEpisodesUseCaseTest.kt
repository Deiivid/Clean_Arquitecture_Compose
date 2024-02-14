package es.clean.architecture.domain.episodes.repository

import es.clean.architecture.data.remote.episodes.paging.EpisodePaging
import io.mockk.mockk
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class CharacterDetailListScreen
class AllEpisodesUseCaseTest {
    @Test
    fun `test AllEpisodesUseCase returns correct flow`() {
        // 1. Preparar el entorno de pruebas
        val mockPagingSource = mockk<EpisodePaging>()
        val limit = 20
        val useCase = AllEpisodesUseCase(mockPagingSource)

        // 2. Llamar al método invoke
        val flow = useCase.invoke(limit)

        // 3. Verificar la configuración del Pager
        // (Esto puede ser complicado y puede necesitar una estrategia diferente dependiendo de tu framework de tests y cómo configuras los mocks)

        // 4. Verificar que se devuelve un flow
        // Aquí puedes añadir tus aserciones para verificar el comportamiento del flow.
        // Puede que necesites configurar el comportamiento esperado de mockPagingSource antes de llamar a useCase.invoke(limit)
    }

}