package com.example.pokedexdesafio.domain.model

import com.example.pokedexdesafio.data.model.AbilityContainer
import com.example.pokedexdesafio.data.model.MoveContainer
import com.example.pokedexdesafio.data.model.TypeContainer

data class PokemonDetail(
    val id: Int,
    val name: String,
    val types: List<String?>,
    val moves: List<String?>,
    val abilities: List<String?>,
    val locationAreaEncounters: String?
)