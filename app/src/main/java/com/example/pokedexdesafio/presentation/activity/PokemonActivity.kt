package com.example.pokedexdesafio.presentation.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pokedexdesafio.R
import com.example.pokedexdesafio.core.functional.Response
import com.example.pokedexdesafio.core.platform.BaseActivity
import com.example.pokedexdesafio.core.utils.KEY_ID
import com.example.pokedexdesafio.domain.model.Pokemon
import com.example.pokedexdesafio.presentation.adapter.PokemonAdapter
import com.example.pokedexdesafio.presentation.viewmodel.PokemonViewModel
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
        viewModel.fetchPokemonList()
        showIndeterminateModalDialog()

        findViewById<Button>(R.id.refresh_button).setOnClickListener { viewModel.fetchPokemonList() }
    }

    override fun onPokemonClick(pokemon: Pokemon) {
        val intent = Intent(this, PokemonDetailActivity::class.java)
            .apply { putExtra(KEY_ID, pokemon.name) }
        startActivity(intent)
    }

    private fun onResponse(response: Response<List<Pokemon>>) {
        hideIndeterminateModalDialog()
        if (response.isSuccess()) {
            if (response.success != null) {
                pokemonAdapter.updateContent(response.success.value)
            }
        } else {
            response.failure?.let { onFailure(it) }
        }
    }

}