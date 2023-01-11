package com.example.pokedexdesafio.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PokemonEntity(
    @PrimaryKey val name: String,
    val url: String
)
