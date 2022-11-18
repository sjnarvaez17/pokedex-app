package com.example.pokedexdesafio.core.di

import android.content.Context
import com.example.pokedexdesafio.AndroidApplication
import com.example.pokedexdesafio.feature.pokemon.GetPokemonRepo
import com.example.pokedexdesafio.feature.pokemon.GetPokemonRepoImpl
import com.example.pokedexdesafio.feature.pokemon.GetPokemonService
import com.example.pokedexdesafio.feature.pokemon_detail.GetPokemonDetailRepoImpl
import com.example.pokedexdesafio.feature.pokemon_detail.GetPokemonDetailService
import com.example.pokedexdesafio.feature.pokemon_detail.GetPokemonDetailsRepo
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class ApplicationModule(private val application: AndroidApplication) {

    @Provides
    @Singleton
    fun provideApplicationContext(): Context = application

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl("https://pokeapi.co/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    @Singleton
    fun provideGetPokemonService(retrofit: Retrofit) = GetPokemonService(retrofit)

    @Provides
    @Singleton
    fun provideGetPokemonRepo(service: GetPokemonService): GetPokemonRepo = GetPokemonRepoImpl(service)

    @Provides
    @Singleton
    fun provideGetPokemonDetailService(retrofit: Retrofit) = GetPokemonDetailService(retrofit)

    @Provides
    @Singleton
    fun provideGetPokemonDetailRepo(service: GetPokemonDetailService): GetPokemonDetailsRepo = GetPokemonDetailRepoImpl(service)

}