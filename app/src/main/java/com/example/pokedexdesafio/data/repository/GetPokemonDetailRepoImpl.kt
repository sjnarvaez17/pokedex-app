package com.example.pokedexdesafio.data.repository

import com.example.pokedexdesafio.core.functional.Response
import com.example.pokedexdesafio.data.services.GetPokemonDetailService
import com.example.pokedexdesafio.domain.model.PokemonDetail
import com.example.pokedexdesafio.domain.repository.GetPokemonDetailsRepo
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class GetPokemonDetailRepoImpl @Inject constructor(private val pokemonDetailService: GetPokemonDetailService) :
    GetPokemonDetailsRepo {

    override fun getPokemonDetails(id: String): Single<Response<PokemonDetail>> =
        Single.fromCallable { pokemonDetailService.fetchPokemonDetails(id) }
            .subscribeOn(Schedulers.io())
}