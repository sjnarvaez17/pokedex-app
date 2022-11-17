package com.example.pokedexdesafio.feature.splash

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.pokedexdesafio.core.platform.BaseActivity
import com.example.pokedexdesafio.feature.pokemon.PokemonActivity

class SplashActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startActivity(Intent(this, PokemonActivity::class.java))
        finish()
    }
}
