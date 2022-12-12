package com.example.pokedexdesafio.data.api

import com.example.pokedexdesafio.domain.model.PokemonDetail
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonDetailApi {

    @GET("/api/v2/pokemon/{pokemonId}")
    fun fetchPokemonDetails(@Path("pokemonId") id: String): Call<PokemonDetail>

}