package com.example.rickymortydn.models.navigation

sealed class Routes(val route: String) {
    object CharacterList : Routes("character_list")
}