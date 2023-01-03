package com.example.pokedexdesafio.data.model

import android.os.Parcelable
import com.example.pokedexdesafio.domain.model.Ability
import com.example.pokedexdesafio.domain.model.Type
import com.example.pokedexdesafio.domain.model.Move
import kotlinx.parcelize.Parcelize

@Parcelize
data class PokemonDetailResponse(
    val id: Int?,
    val name: String,
    val types: List<TypeContainerResponse>?,
    // no info about evolve
    val moves: List<MoveContainerResponse>?,
    val abilities: List<AbilityContainerResponse>?,
    val locationAreaEncounters: String
) : Parcelable

//Type
@Parcelize
data class TypeContainerResponse(val slot: Int?, val type: TypeResponse?) : Parcelable

@Parcelize
data class TypeResponse(val name: String?, val url: String?) : Parcelable

//Attacks
@Parcelize
data class MoveContainerResponse(val move: MoveResponse?) : Parcelable

@Parcelize
data class MoveResponse(val name: String?, val url: String?) : Parcelable

//Abilities
@Parcelize
data class AbilityContainerResponse(val ability: AbilityResponse?, val isHidden: Boolean?, val slot: Int?) : Parcelable

@Parcelize
data class AbilityResponse(val name: String?, val url: String?) : Parcelable

// Ext functions for mapping into business models
fun TypeResponse.toType(): Type? =
    if (name.isNullOrBlank() || url.isNullOrBlank()) {
        null
    } else {
        Type(name, url)
    }

fun MoveResponse.toMove(): Move? =
    if (name.isNullOrBlank() || url.isNullOrBlank()) {
        null
    } else {
        Move(name, url)
    }

fun AbilityResponse.toAbility(): Ability? =
    if (name.isNullOrBlank() || url.isNullOrBlank()) {
        null
    } else {
        Ability(name, url)
    }

