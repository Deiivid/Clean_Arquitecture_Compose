package es.clean.architecture.ui.views.characters.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import es.clean.architecture.domain.characters.models.character.RickyMortyCharacterModel
import es.clean.architecture.domain.characters.repository.characters.AllCharactersUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
open class CharactersViewModel @Inject constructor(
    allCharactersUseCase: AllCharactersUseCase
) : ViewModel() {
    companion object{
        const val LIMIT = 20

    }
    val searchQuery = MutableStateFlow("")

    val allCharacters: StateFlow<PagingData<RickyMortyCharacterModel.RickyMortyCharacter>> =
        searchQuery.flatMapLatest { query ->
                allCharactersUseCase(query, LIMIT).cachedIn(viewModelScope)
        }.stateIn(viewModelScope, SharingStarted.Lazily, PagingData.empty())


    fun searchCharacters(query: String) {
        Log.d("Search", "Query: $query")
        if (searchQuery.value != query) {
            searchQuery.value = query
        }
    }
}
