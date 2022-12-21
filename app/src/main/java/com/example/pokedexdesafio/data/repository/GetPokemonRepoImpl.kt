package com.example.pokedexdesafio.data.repository

import com.example.pokedexdesafio.data.services.GetPokemonService
import com.example.pokedexdesafio.data.model.PokemonContainerResponse
import com.example.pokedexdesafio.domain.repository.GetPokemonRepo
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Response
import javax.inject.Inject

class GetPokemonRepoImpl @Inject constructor(private val pokemonService: GetPokemonService) :
    GetPokemonRepo {

    override fun getPokemonList(): Single<Response<PokemonContainerResponse>> =
        Single.fromCallable { pokemonService.fetchPokemonList() }
            .subscribeOn(Schedulers.io())
}