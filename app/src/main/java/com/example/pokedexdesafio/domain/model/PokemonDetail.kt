package com.example.pokedexdesafio.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PokemonDetail(
    val id: Int,
    val name: String,
    val types: List<TypeContainer?>,
    // no info about evolve
    val moves: List<MoveContainer?>,
    val abilities: List<AbilityContainer?>,
    val locationAreaEncounters: String?
) : Parcelable

//Type
@Parcelize
data class TypeContainer(val slot: Int, val type: Type) : Parcelable

@Parcelize
data class Type(val name: String, val url: String) : Parcelable

//Attacks
@Parcelize
data class MoveContainer(val move: Move) : Parcelable

@Parcelize
data class Move(val name: String, val url: String) : Parcelable

//Abilities
@Parcelize
data class AbilityContainer(val ability: Ability, val isHidden: Boolean, val slot: Int) : Parcelable

@Parcelize
data class Ability(val name: String, val url: String) : Parcelable
