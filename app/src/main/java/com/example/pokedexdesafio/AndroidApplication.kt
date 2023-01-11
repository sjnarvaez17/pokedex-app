package com.example.pokedexdesafio

import android.app.Application
import com.example.pokedexdesafio.core.di.ApplicationComponent
import com.example.pokedexdesafio.core.di.ApplicationModule
import com.example.pokedexdesafio.core.di.DaggerApplicationComponent
import com.example.pokedexdesafio.core.di.RoomModule

class AndroidApplication : Application() {

    val appComponent: ApplicationComponent by lazy(mode = LazyThreadSafetyMode.NONE) {
        DaggerApplicationComponent
            .builder()
            .applicationModule(ApplicationModule(this))
            .applicationModule(RoomModule(this))
            .build()
    }

    override fun onCreate() {
        super.onCreate()
        appComponent.inject(this)
    }
}