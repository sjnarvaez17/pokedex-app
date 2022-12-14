package com.example.pokedexdesafio.data.api

import com.example.pokedexdesafio.data.model.PokemonDetailResponse
import com.example.pokedexdesafio.data.model.PokemonContainerResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonApi {

    companion object {
        private const val DEFAULT_LIMIT = 150
        private const val DEFAULT_OFFSET = 0
    }

    @GET("/api/v2/pokemon")
    fun fetchPokemonList(
        @Query("limit") limit: Int = DEFAULT_LIMIT,
        @Query("offset") offset: Int = DEFAULT_OFFSET
    ): Call<PokemonContainerResponse>

    @GET("/api/v2/pokemon/{pokemonId}")
    fun fetchPokemonDetails(@Path("pokemonId") id: String): Call<PokemonDetailResponse>

}