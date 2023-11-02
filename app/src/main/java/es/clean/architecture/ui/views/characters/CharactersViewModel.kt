package es.clean.architecture.ui.views.characters

import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import dagger.hilt.android.lifecycle.HiltViewModel
import es.clean.architecture.domain.characters.AllCharactersUseCase
import es.clean.architecture.domain.characters.models.character.RickyMortyCharacterModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
open class CharactersViewModel @Inject constructor(
    allCharactersUseCase: AllCharactersUseCase
) : ViewModel() {
    val allCharacters: Flow<PagingData<RickyMortyCharacterModel.RickyMortyCharacter>> = allCharactersUseCase(20)
}