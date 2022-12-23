package com.example.pokedexdesafio.domain.repository

import com.example.pokedexdesafio.core.functional.Response
import com.example.pokedexdesafio.domain.model.Pokemon
import io.reactivex.rxjava3.core.Single

interface GetPokemonRepo {

    fun getPokemonList(): Single<Response<List<Pokemon>>>
}