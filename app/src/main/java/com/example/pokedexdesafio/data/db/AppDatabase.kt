package com.example.pokedexdesafio.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.pokedexdesafio.data.db.dao.PokemonDao
import com.example.pokedexdesafio.data.db.entities.PokemonEntity

@Database(entities = [PokemonEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun pokemonDao(): PokemonDao
}