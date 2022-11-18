package com.example.pokedexdesafio.feature.pokemon

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PokemonResponse(val count: Int, val next: String, val results: List<Pokemon>) :
    Parcelable

@Parcelize
data class Pokemon(val id: Int?, val name: String?, val url: String?) : Parcelable