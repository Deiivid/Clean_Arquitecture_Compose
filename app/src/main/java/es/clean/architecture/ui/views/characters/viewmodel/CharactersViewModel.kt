package es.clean.architecture.ui.views.characters.viewmodel

/*@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val getCharacterUseCase: CharacterUseCase
) : ViewModel() {
    private val _charactersSearched by lazy { MutableStateFlow<ResourceState<*>>(ResourceState.Idle) }
    val charactersSearched: StateFlow<ResourceState<*>>
        get() = _charactersSearched

    fun fetchCharacters(): Flow<PagingData<RickyMortyCharacterModel.RickyMortyCharacter>> {
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
*/
