package com.example.pokedexdesafio.domain.repository

import com.example.pokedexdesafio.domain.model.PokemonDetail
import io.reactivex.rxjava3.core.Single
import retrofit2.Response

interface GetPokemonDetailsRepo {

    fun getPokemonDetails(id: String): Single<Response<PokemonDetail>>
}