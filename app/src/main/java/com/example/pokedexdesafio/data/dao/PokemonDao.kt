package com.example.pokedexdesafio.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.pokedexdesafio.data.entities.PokemonEntity
import com.example.pokedexdesafio.domain.model.Pokemon


@Dao
interface PokemonDao {

    @Query("SELECT * FROM pokemon_entity")
    fun getAllPokemons(): List<Pokemon>

    @Insert
    fun insertPokemon(newPokemon: PokemonEntity)
}