package com.example.pokedexdesafio.feature.pokemon_detail

import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Response
import javax.inject.Inject

class GetPokemonDetailRepoImpl @Inject constructor(private val pokemonDetailService: GetPokemonDetailService) :
    GetPokemonDetailsRepo {
    override fun getPokemonDetails(id: String): Single<Response<PokemonDetail>> =
        Single.fromCallable { pokemonDetailService.fetchPokemonDetails(id) }
            .subscribeOn(Schedulers.io())
}