package com.example.pokedexdesafio.feature.pokemon_detail

import javax.inject.Inject

class GetPokemonDetailUseCase @Inject constructor(private val repository: GetPokemonDetailsRepo) {
    operator fun invoke(pokemonId: String) = repository.getPokemonDetails(pokemonId)
}