package com.example.pokedexdesafio.feature.pokemon_detail

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonDetailApi {

    @GET("/pokemon/{pokemonId}")
    fun fetchPokemonDetails(@Path("pokemonId") id: String): Call<PokemonDetail>

}