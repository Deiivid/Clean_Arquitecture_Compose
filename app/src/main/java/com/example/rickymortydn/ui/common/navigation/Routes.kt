package com.example.rickymortydn.ui.common.navigation

sealed class Routes(val route: String) {
    object CharacterList : Routes("character_list")
}