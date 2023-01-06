package com.example.pokedexdesafio.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import com.example.pokedexdesafio.domain.model.PokemonDetail

@Dao
interface PokemonDetailDao {

    @Insert
    fun insertPokemonDetail(pokemonDetail: PokemonDetail)
}