package com.example.pokedexdesafio.core.di

import com.example.pokedexdesafio.AndroidApplication
import com.example.pokedexdesafio.presentation.activity.PokemonActivity
import com.example.pokedexdesafio.presentation.activity.PokemonDetailActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {

    fun inject(application: AndroidApplication)
    fun inject(pokemonActivity: PokemonActivity)
    fun inject(pokemonDetailActivity: PokemonDetailActivity)
}