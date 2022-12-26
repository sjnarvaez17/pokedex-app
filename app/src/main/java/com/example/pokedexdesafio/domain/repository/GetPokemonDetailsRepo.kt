package com.example.pokedexdesafio.domain.repository

import com.example.pokedexdesafio.core.functional.Response
import com.example.pokedexdesafio.domain.model.PokemonDetail
import io.reactivex.rxjava3.core.Single

interface GetPokemonDetailsRepo {

    fun getPokemonDetails(id: String): Single<Response<PokemonDetail>>
}