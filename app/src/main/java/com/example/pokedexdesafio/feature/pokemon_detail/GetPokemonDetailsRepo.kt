package com.example.pokedexdesafio.feature.pokemon_detail

import io.reactivex.rxjava3.core.Single
import retrofit2.Response

interface GetPokemonDetailsRepo {

    fun getPokemonDetails(id: String): Single<Response<PokemonDetail>>
}