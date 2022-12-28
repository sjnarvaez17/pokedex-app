package com.example.pokedexdesafio.data.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.pokedexdesafio.domain.model.Pokemon


@Dao
interface PokemonDao {

    @Query("SELECT * FROM pokemon_entity")
    fun getAllPokemons(): List<Pokemon>
}