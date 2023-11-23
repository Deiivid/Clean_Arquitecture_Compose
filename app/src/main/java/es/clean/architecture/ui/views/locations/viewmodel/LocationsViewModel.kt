package es.clean.architecture.ui.views.locations.viewmodel

import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import dagger.hilt.android.lifecycle.HiltViewModel
import es.clean.architecture.domain.episodes.models.RickyMortyEpisodesModel
import es.clean.architecture.domain.episodes.repository.AllEpisodesUseCase
import es.clean.architecture.domain.locations.models.RickyMortyLocationsModel
import es.clean.architecture.domain.locations.repository.AllLocationsUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
open class LocationsViewModel @Inject constructor(
    allLocationsUseCase: AllLocationsUseCase
) : ViewModel() {
    companion object{
        const val LIMIT = 20

    }
    val allLocations: Flow<PagingData<RickyMortyLocationsModel.Location>> = allLocationsUseCase(LIMIT)

}
