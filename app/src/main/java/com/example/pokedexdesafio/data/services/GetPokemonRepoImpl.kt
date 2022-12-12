package com.example.pokedexdesafio.data.services

import com.example.pokedexdesafio.data.services.GetPokemonService
import com.example.pokedexdesafio.domain.model.PokemonResponse
import com.example.pokedexdesafio.domain.repository.GetPokemonRepo
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Response
import javax.inject.Inject

class GetPokemonRepoImpl @Inject constructor(private val pokemonService: GetPokemonService) :
    GetPokemonRepo {

    override fun getPokemonList(): Single<Response<PokemonResponse>> =
        Single.fromCallable { pokemonService.fetchPokemonList() }
            .subscribeOn(Schedulers.io())
}