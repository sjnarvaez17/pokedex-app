package com.example.pokedexdesafio.feature.pokemon

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PokemonApi {


    companion object {
        private const val DEFAULT_LIMIT = 150
        private const val DEFAULT_OFFSET = 0
    }

    @GET("/pokemon")
    fun fetchPokemonList(
        @Query("limit") limit: Int = DEFAULT_LIMIT,
        @Query("offset") offset: Int = DEFAULT_OFFSET
    ): Call<List<Pokemon>>
}