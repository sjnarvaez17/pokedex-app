package com.example.pokedexdesafio.core.di

import androidx.room.Room
import com.example.pokedexdesafio.AndroidApplication
import com.example.pokedexdesafio.data.db.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RoomModule(private val application: AndroidApplication) {

    @Provides
    @Singleton
    fun provideRommDatabase(): AppDatabase =
        Room.databaseBuilder(application.applicationContext, AppDatabase::class.java, "room-db")
            .build()
}