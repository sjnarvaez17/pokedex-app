package com.example.pokedexdesafio.domain.model

data class PokemonDetail(
    val id: Int,
    val name: String,
    val types: List<Type>,
    val moves: List<Move>,
    val abilities: List<Ability>,
    val locationAreaEncounters: String?
)

data class Type(val name: String, val url: String)

data class Move(val name: String, val url: String)

data class Ability(val name: String, val url: String)