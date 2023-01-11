package com.example.pokedexdesafio.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.pokedexdesafio.data.db.entities.PokemonEntity

@Dao
interface PokemonDao {

    @Insert
    fun insertPokemon(newPokemon: PokemonEntity)

    @Query("SELECT * FROM PokemonEntity")
    fun getAllPokemons(): List<PokemonEntity>
}
