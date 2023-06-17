package com.example.rickymortydn.domain.characters

import android.util.Log
import com.example.rickymortydn.data.characters.CharacterRepositoryImpl
import com.example.rickymortydn.data.remote.RickyMortyService
import com.example.rickymortydn.models.CharacterModel
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test


class CharacterRepositoryImplTest {

    private val apiService = mockk<RickyMortyService>()
    private val repository = CharacterRepositoryImpl(apiService)

    @Test
    fun `getCharacters should return list of characters`() = runBlocking {
        // Simular una lista de personajes de prueba
        val characters = listOf(
            CharacterModel(
                CharacterModel.Info(1, "", 0, ""),
                listOf(
                    CharacterModel.Result(
                        "", emptyList(), "", 1, "",
                        CharacterModel.Result.Location("", ""),
                        "",
                        CharacterModel.Result.Origin("", ""),
                        "", "", ""
                    ),
                    CharacterModel.Result(
                        "",
                        emptyList(),
                        "",
                        2,
                        "",
                        CharacterModel.Result.Location("", ""),
                        "",
                        CharacterModel.Result.Origin("", ""),
                        "",
                        "",
                        ""
                    )
                )
            )
        )

        // Configurar el comportamiento del apiService mock
        coEvery { apiService.getCharacters() } returns characters

        // Llamar al método getCharacters() del repositorio
        val result = repository.getCharacters()
        println(result)
        // Verificar que el resultado coincida con la lista de personajes simulada
        assertEquals(characters, result)
    }

}