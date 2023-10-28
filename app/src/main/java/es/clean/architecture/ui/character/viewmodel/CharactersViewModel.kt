package es.clean.architecture.ui.character.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import es.clean.architecture.domain.characters.CharacterUseCase
import es.clean.architecture.models.CharacterModel
import es.clean.architecture.ui.common.states.ResourceState
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
    private val _charactersSearched by lazy {
        MutableStateFlow<ResourceState<List<CharacterModel.CharacterResult>>>(
            ResourceState.Idle()
        )
    }
    val charactersSearched: StateFlow<ResourceState<List<CharacterModel.CharacterResult>>>
        get() = _charactersSearched

    fun fetchCharacters() {
        /*        _charactersSearched.update {
                    ResourceState.Loading()
                }
          */      viewModelScope.launch(Dispatchers.IO) {

            characterUseCase().collectLatest { characters ->
                _charactersSearched.update {
                    if (characters.isNotEmpty())
                        ResourceState.Success(characters)
                    else
                        ResourceState.Error(InvalidObjectException("Characters not found"))
                }
            }
        }
    }
}