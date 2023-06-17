package com.example.rickymortydn.models


import androidx.annotation.Keep

@Keep
data class CharacterModel(
    val info: Info,
    val results: List<Result>
) {
    @Keep
    data class Info(
        val count: Int,
        val next: String,
        val pages: Int,
        val prev: Any
    )

    @Keep
    data class Result(
        val created: String,
        val episode: List<String>,
        val gender: String,
        val id: Int,
        val image: String,
        val location: Location,
        val name: String,
        val origin: Origin,
        val species: String,
        val status: String,
        val type: String
    ) {
        @Keep
        data class Location(
            val name: String,
            val url: String
        )

        @Keep
        data class Origin(
            val name: String,
            val url: String
        )
    }
}