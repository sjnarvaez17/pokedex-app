package com.example.pokedexdesafio.presentation.activity

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.pokedexdesafio.R
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
        viewModel.failure.observe(this@PokemonDetailActivity) { onFailure(it) }

        val pokemonId = intent.getStringExtra(KEY_ID)
        if (pokemonId.isNullOrBlank()) {
            Toast.makeText(this, getString(R.string.no_param_error), Toast.LENGTH_LONG).show()
                .also { finish() }
        } else {
            viewModel.fetchPokemonDetails(pokemonId)
            showIndeterminateModalDialog()
        }
    }

    private fun onResponse(response: Response<PokemonDetail>) {
        hideIndeterminateModalDialog()
        if (response.isSuccess()) {
            val pokemonDetail = response.success?.value
            if (pokemonDetail == null) {
                Toast.makeText(this, getString(R.string.error_network), Toast.LENGTH_LONG).show()
            } else {
                updateUI(pokemonDetail)
            }
        } else {
            Toast.makeText(this, getString(R.string.error_network), Toast.LENGTH_LONG).show()
        }
    }

    private fun updateUI(details: PokemonDetail) {
        title = details.name
        Glide.with(this@PokemonDetailActivity)
            .load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${details.id}.png")
            .into(findViewById(R.id.pokemonImage))

        findViewById<TextView>(R.id.pokemonType).text =
            details.types.filterNotNull().joinToString(separator = " - ") { it.name.toString() }
        //TODO Review it.type?.name.toString
    }
}