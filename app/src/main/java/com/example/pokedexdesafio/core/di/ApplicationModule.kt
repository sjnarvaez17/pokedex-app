package com.example.pokedexdesafio.core.di

import android.content.Context
import androidx.room.Room
import com.example.pokedexdesafio.AndroidApplication
import com.example.pokedexdesafio.data.db.AppDatabase
import com.example.pokedexdesafio.domain.repository.GetPokemonRepo
import com.example.pokedexdesafio.data.repository.GetPokemonRepoImpl
import com.example.pokedexdesafio.data.services.GetPokemonService
import com.example.pokedexdesafio.data.repository.GetPokemonDetailRepoImpl
import com.example.pokedexdesafio.data.services.GetPokemonDetailService
import com.example.pokedexdesafio.domain.repository.GetPokemonDetailsRepo
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
    fun provideRoomDatabase(): AppDatabase =
        Room.databaseBuilder(application, AppDatabase::class.java, "room-db")
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