package com.example.pokedexdesafio.domain.use_case

import com.example.pokedexdesafio.domain.repository.GetPokemonDetailsRepo
import javax.inject.Inject

class GetPokemonDetailUseCase @Inject constructor(private val repository: GetPokemonDetailsRepo) {

    operator fun invoke(pokemonId: String) = repository.getPokemonDetails(pokemonId)
}