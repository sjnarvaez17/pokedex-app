package com.example.pokedexdesafio.feature.pokemon

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Pokemon(val id: Int?, val name: String?, val url: String?) : Parcelable