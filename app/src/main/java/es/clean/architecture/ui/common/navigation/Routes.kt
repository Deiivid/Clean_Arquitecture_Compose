package es.clean.architecture.ui.common.navigation

sealed class Routes(val route: String) {
    object CharacterList : Routes("character_list")
    object CharacterDetailScreen : Routes("character_detail/{character}")
    object Splash : Routes("splash_screen")
}