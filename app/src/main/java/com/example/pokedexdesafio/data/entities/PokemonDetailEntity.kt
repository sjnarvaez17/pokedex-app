package com.example.pokedexdesafio.data.entities

import androidx.room.Entity

@Entity(tableName = "pokemon_detail_entity")
data class PokemonDetailEntity(

    val id: Int,
    val name: String,
    val types: List<String>,
    val moves: List<String>,
    val abilities: List<String>,
    val locationAreaEncounters: String
)

