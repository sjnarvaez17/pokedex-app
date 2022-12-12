package com.example.pokedexdesafio.domain.pokemon

import com.example.pokedexdesafio.domain.repository.GetPokemonRepo
import javax.inject.Inject

class PokemonUseCase @Inject constructor(private val pokemonRepo: GetPokemonRepo){

    operator fun invoke() = pokemonRepo.getPokemonList()
}