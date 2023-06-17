package com.example.rickymortydn.ui.character.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickymortydn.data.api.RickyMortyApiClient
import com.example.rickymortydn.data.characters.CharacterRepositoryImpl
import com.example.rickymortydn.domain.characters.CharacterUseCase
import com.example.rickymortydn.models.CharacterModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CharactersViewModel() : ViewModel() {

    private val _characters = MutableStateFlow<List<CharacterModel>>(emptyList())
    val characters: StateFlow<List<CharacterModel>> = _characters
    private val characterRepository = CharacterRepositoryImpl(RickyMortyApiClient().apiService)
    private val characterUseCase = CharacterUseCase(characterRepository)

    fun fetchCharacters() {
        viewModelScope.launch {
            try {
                val result = characterUseCase.getCharacters()
                _characters.value = result
            } catch (e: Exception) {
                // Manejar el error
            }
        }
    }
}