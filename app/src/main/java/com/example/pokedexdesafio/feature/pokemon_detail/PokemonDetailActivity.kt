package com.example.pokedexdesafio.feature.pokemon_detail

import android.os.Bundle
import android.widget.Toast
import com.example.pokedexdesafio.R
import com.example.pokedexdesafio.core.platform.BaseActivity
import com.example.pokedexdesafio.core.utils.KEY_ID
import retrofit2.Response
import javax.inject.Inject

class PokemonDetailActivity : BaseActivity(){

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
        } else {
            viewModel.fetchPokemonDetails(pokemonId)
            showIndeterminateModalDialog()
        }
    }

    private fun onResponse(response: Response<PokemonDetail>) {
        hideIndeterminateModalDialog()
        if (response.isSuccessful) {
            val pokemonDetail = response.body()
            if (pokemonDetail == null) {
                Toast.makeText(this, getString(R.string.network_error), Toast.LENGTH_LONG).show()
            } else {
                updateUI(pokemonDetail)
            }
        } else {
            Toast.makeText(this, getString(R.string.network_error), Toast.LENGTH_LONG).show()
        }
    }

    private fun updateUI(details: PokemonDetail) {
        val EMPTY_STRING = getString(R.string.empty_string)
        this@PokemonDetailActivity.title = details.types.toString()


    }
}