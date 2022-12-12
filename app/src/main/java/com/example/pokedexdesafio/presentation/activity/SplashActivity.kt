package com.example.pokedexdesafio.presentation.activity

import android.content.Intent
import android.os.Bundle
import com.example.pokedexdesafio.core.platform.BaseActivity

class SplashActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startActivity(Intent(this, PokemonActivity::class.java))
        finish()
    }
}
