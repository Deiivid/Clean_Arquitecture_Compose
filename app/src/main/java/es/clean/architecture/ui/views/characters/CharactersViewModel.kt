package es.clean.architecture.ui.character

import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import es.clean.architecture.domain.characters.AllCharactersUseCase
import es.clean.architecture.models.CharacterModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

open class CharactersViewModel @Inject constructor(
    allCharactersUseCase: AllCharactersUseCase
) : ViewModel() {
    val allCharacters: Flow<PagingData<CharacterModel.CharacterResult>> = allCharactersUseCase(20)
}