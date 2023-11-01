package es.clean.architecture.ui.character.viewmodel

/*@HiltViewModel
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
}*/