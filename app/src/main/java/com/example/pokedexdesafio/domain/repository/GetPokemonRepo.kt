package com.example.pokedexdesafio.domain.repository

import com.example.pokedexdesafio.data.model.PokemonResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.Response

interface GetPokemonRepo {

    fun getPokemonList(): Single<Response<PokemonResponse>>
}