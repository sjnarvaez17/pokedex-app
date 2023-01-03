package com.example.pokedexdesafio.presentation.activity

import android.os.Bundle
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.pokedexdesafio.R
import com.example.pokedexdesafio.core.functional.Failure
import com.example.pokedexdesafio.core.functional.Response
import com.example.pokedexdesafio.core.platform.BaseActivity
import com.example.pokedexdesafio.core.utils.KEY_ID
import com.example.pokedexdesafio.domain.model.PokemonDetail
import com.example.pokedexdesafio.presentation.viewmodel.PokemonDetailViewModel
import javax.inject.Inject

class PokemonDetailActivity : BaseActivity() {

    @Inject
    lateinit var viewModel: PokemonDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokemon_detail)
        appComponent.inject(this)

        viewModel.pokemonDetails.observe(this@PokemonDetailActivity) { onResponse(it) }

        val pokemonId = intent.getStringExtra(KEY_ID)
        if (pokemonId.isNullOrBlank()) {
            onFailure(Failure.ExpectedParamMissing).also { finish() }
        } else {
            viewModel.fetchPokemonDetails(pokemonId)
            showIndeterminateModalDialog()
        }
    }

    private fun onResponse(response: Response<PokemonDetail>) {
        hideIndeterminateModalDialog()
        if (response.isSuccess()) {
            if (response.success != null) {
                updateUI(response.success.value)
            }
        } else {
            response.failure?.let { onFailure(it) }
        }
    }

    private fun updateUI(details: PokemonDetail) {
        title = details.name
        Glide.with(this@PokemonDetailActivity)
            .load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${details.id}.png")
            .into(findViewById(R.id.pokemonImage))

        findViewById<TextView>(R.id.pokemonType).text =
            details.types.joinToString(separator = " - ") { it.name }
    }
}