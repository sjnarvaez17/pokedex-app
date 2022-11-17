package com.example.pokedexdesafio.feature.pokemon

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pokedexdesafio.R
import com.example.pokedexdesafio.core.platform.BaseActivity
import com.example.pokedexdesafio.core.utils.KEY_ID
import com.example.pokedexdesafio.feature.pokemon_detail.PokemonDetailActivity
import retrofit2.Response
import javax.inject.Inject

class PokemonActivity : BaseActivity(), PokemonAdapter.PokemonListener {

    @Inject
    lateinit var viewModel: PokemonViewModel
    private lateinit var pokemonAdapter: PokemonAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContentView(R.layout.activity_pokemon)
        appComponent.inject(this)

        val adapterLayoutManager = LinearLayoutManager(this)
        pokemonAdapter = PokemonAdapter(emptyList(), this)
        findViewById<RecyclerView>(R.id.recycler).apply {
            layoutManager = adapterLayoutManager
            adapter = pokemonAdapter
        }

        viewModel.pokemonList.observe(this@PokemonActivity) { onResponse(it) }
        viewModel.failure.observe(this@PokemonActivity) { onFailure(it) }
        viewModel.fetchPokemonList()
        showIndeterminateModalDialog()
    }

    override fun onPokemonClick(pokemon: Pokemon) {
        val intent = Intent(this, PokemonDetailActivity::class.java)
            .apply { putExtra(KEY_ID, pokemon.name) }
        startActivity(intent)
    }

    private fun onResponse(response: Response<List<Pokemon>>) {
        hideIndeterminateModalDialog()
        if (response.isSuccessful) {
            val pokemons = response.body()
            if (pokemons == null) {
                Toast.makeText(this, getString(R.string.network_error), Toast.LENGTH_LONG).show()
            } else {
                pokemonAdapter.updateContent(pokemons)
            }
        } else {
            Toast.makeText(this, getString(R.string.network_error), Toast.LENGTH_LONG).show()
        }
    }

}