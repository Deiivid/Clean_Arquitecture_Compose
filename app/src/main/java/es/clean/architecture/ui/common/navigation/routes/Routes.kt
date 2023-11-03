package es.clean.architecture.ui.common.navigation.routes

import es.clean.architecture.ui.common.CHARACTER_OBJECT
import es.clean.architecture.ui.common.EPISODE_OBJECT
import es.clean.architecture.ui.common.LOCATION_OBJECT

sealed class Routes(val route: String) {

    //region [Characters]
    data object CharacterList : Routes("character_list")
    data object CharacterDetailScreen : Routes("character_detail/{$CHARACTER_OBJECT}")
    //endregion [Characters]

    //region [Episode]
    data object EpisodeListScreen : Routes("episode_list")
    data object EpisodeDetailScreen : Routes("episode_detail/{$EPISODE_OBJECT}")
    //endregion [Episode]


    //region [Locations]
    data object LocationListScreen : Routes("location_list")
    data object LocationDetailScreen : Routes("location_detail/{$LOCATION_OBJECT}")
    //endregion [Locations]

    //region [Splash Screen]
    data object Splash : Routes("splash_screen")
    //endregion [Splash Screen]

}
