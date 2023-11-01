package es.clean.architecture.ui.views.characters.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import es.clean.architecture.domain.characters.CharacterUseCase
import es.clean.architecture.domain.characters.models.character.RickyMortyCharacter
import es.clean.architecture.ui.common.states.ResourceState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val getCharacterUseCase: CharacterUseCase
) : ViewModel() {
    private val _charactersSearched by lazy { MutableStateFlow<ResourceState<*>>(ResourceState.Idle) }
    val charactersSearched: StateFlow<ResourceState<*>>
        get() = _charactersSearched

    fun fetchCharacters(): Flow<PagingData<RickyMortyCharacter.Character>> {
        return getCharacterUseCase()
            .catch { error ->
                _charactersSearched.update { ResourceState.Error(error) }
            }
            .onStart {
                _charactersSearched.update { ResourceState.Loading("") }
            }
            .map { pagingData ->
                delay(1000)
                _charactersSearched.update { ResourceState.Success("") }
                pagingData
            }
            .cachedIn(viewModelScope)
    }
}

