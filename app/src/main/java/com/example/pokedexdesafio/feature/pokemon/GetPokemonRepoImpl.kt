package com.example.pokedexdesafio.feature.pokemon

import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Response
import javax.inject.Inject

class GetPokemonRepoImpl @Inject constructor(private val pokemonService: GetPokemonService) :
    GetPokemonRepo {

    override fun getPokemonList(): Single<Response<List<Pokemon>>> =
        Single.fromCallable { pokemonService.fetchPokemonList() }
            .subscribeOn(Schedulers.io())
}