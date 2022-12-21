package com.example.pokedexdesafio.domain.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.pokedexdesafio.domain.model.AbilityContainer
import com.example.pokedexdesafio.domain.model.MoveContainer
import com.example.pokedexdesafio.domain.model.TypeContainer

@Entity
data class PokemonEntity (
    @PrimaryKey val id: Int?,
    @ColumnInfo(name= "name") val name: String?,
    @ColumnInfo(name= "types") val types: List<String?>,
    @ColumnInfo(name= "moves") val moves: List<MoveContainer?>,
    @ColumnInfo(name= "abilities") val abilities: List<AbilityContainer?>,
    @ColumnInfo(name= "locationAreaEncounters") val locationAreaEncounters: String?

    )