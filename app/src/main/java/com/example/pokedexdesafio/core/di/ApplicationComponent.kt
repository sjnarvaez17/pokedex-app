package com.example.pokedexdesafio.core.di

import com.example.pokedexdesafio.AndroidApplication
import com.example.pokedexdesafio.feature.pokemon.PokemonActivity
import com.example.pokedexdesafio.feature.pokemon_detail.PokemonDetailActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {

    fun inject(application: AndroidApplication)
    fun inject(pokemonActivity: PokemonActivity)
    fun inject(pokemonDetailActivity: PokemonDetailActivity)
}