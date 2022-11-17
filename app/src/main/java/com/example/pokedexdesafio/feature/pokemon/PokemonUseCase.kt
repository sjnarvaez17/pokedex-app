package com.example.pokedexdesafio.feature.pokemon

import javax.inject.Inject

class PokemonUseCase @Inject constructor(private val pokemonRepo: GetPokemonRepo){

    operator fun invoke() = pokemonRepo.getPokemonList()
}