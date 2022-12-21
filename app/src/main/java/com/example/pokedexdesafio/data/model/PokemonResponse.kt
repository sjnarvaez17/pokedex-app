package com.example.pokedexdesafio.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PokemonContainerResponse(val count: Int, val next: String, val results: List<PokemonResponse>) :
    Parcelable

@Parcelize
data class PokemonResponse(val id: Int?, val name: String?, val url: String?) : Parcelable