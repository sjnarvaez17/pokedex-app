package com.example.pokedexdesafio.core.di

import android.content.Context
import com.example.pokedexdesafio.AndroidApplication
import com.example.pokedexdesafio.feature.pokemon.GetPokemonRepo
import com.example.pokedexdesafio.feature.pokemon.GetPokemonRepoImpl
import com.example.pokedexdesafio.feature.pokemon.GetPokemonService
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
        .baseUrl("https://pokeapi.co/api/v2/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    @Singleton
    fun provideGetPokemonService(retrofit: Retrofit) = GetPokemonService(retrofit)

    @Provides
    @Singleton
    fun provideGetPokemonRepo(service: GetPokemonService): GetPokemonRepo = GetPokemonRepoImpl(service)

}