package com.example.pokedexdesafio.data.model

import android.os.Parcelable
import com.example.pokedexdesafio.domain.model.Pokemon
import kotlinx.parcelize.Parcelize

@Parcelize
data class PokemonContainerResponse(
    val count: Int,
    val next: String,
    val results: List<PokemonResponse>
) : Parcelable

@Parcelize
data class PokemonResponse(val id: Int?, val name: String?, val url: String?) : Parcelable

// Ext functions for mapping into business models
fun PokemonResponse.toPokemon(): Pokemon? =
    if (id == null || name.isNullOrBlank() || url.isNullOrBlank()) {
        null
    } else {
        Pokemon(id, name, url)
    }

fun PokemonContainerResponse.toPokemonList(): List<Pokemon> = results.mapNotNull { it.toPokemon() }