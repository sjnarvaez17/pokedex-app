package com.example.pokedexdesafio.feature.pokemon

import io.reactivex.rxjava3.core.Single
import retrofit2.Response

interface GetPokemonRepo {

    fun getPokemonList(): Single<Response<PokemonResponse>>
}