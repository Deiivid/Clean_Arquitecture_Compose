package com.example.rickymortydn.ui.character.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickymortydn.common.states.ResourceState
import com.example.rickymortydn.domain.characters.CharacterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.InvalidObjectException
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val characterUseCase: CharacterUseCase
) : ViewModel() {
    private val _charactersSearched by lazy { MutableStateFlow<ResourceState<*>>(ResourceState.Idle) }
    val charactersSearched: StateFlow<ResourceState<*>>
        get() = _charactersSearched

    fun fetchCharacters() {
        _charactersSearched.update { ResourceState.Loading("") }
        viewModelScope.launch(Dispatchers.IO) {

            characterUseCase().collectLatest { characters ->
                _charactersSearched.update {
                    if (characters.isNotEmpty())
                        ResourceState.Success(characters)
                    else
                        ResourceState.Error(InvalidObjectException("SuperHero not found :("))
                }
            }
        }
    }
}