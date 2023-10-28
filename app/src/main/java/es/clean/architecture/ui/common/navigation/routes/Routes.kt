package es.clean.architecture.ui.common.navigation.routes

sealed class Routes(val route: String) {

    //region [Characters]
    data object CharacterList : Routes("character_list")
    data object CharacterDetailScreen : Routes("character_detail/{character}")

    //endregion [Characters]

    //region [Splash Screen]
    data object Splash : Routes("splash_screen")
    //endregion [Splash Screen]

}
