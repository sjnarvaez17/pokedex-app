package com.example.pokedexdesafio.domain.model

data class PokemonDetail(
    val id: Int,
    val name: String,
    val types: List<TypeList?>,
    val moves: List<MoveList?>,
    val abilities: List<AbilityList?>,
    val locationAreaEncounters: String?
)

data class TypeList(val slot: Int, val type: TypeDetail)


data class TypeDetail(val name: String, val url: String)


data class MoveList(val move: MoveDetail)


data class MoveDetail(val name: String, val url: String)

data class AbilityList(val ability: AbilityDetail, val isHidden: Boolean, val slot: Int)


data class AbilityDetail(val name: String, val url: String)