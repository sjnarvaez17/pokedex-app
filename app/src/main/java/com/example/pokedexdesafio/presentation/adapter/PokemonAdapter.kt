package com.example.pokedexdesafio.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.pokedexdesafio.R
import com.example.pokedexdesafio.data.model.PokemonResponse

class PokemonAdapter(private var pokemons: List<PokemonResponse>, private val listener: PokemonListener) :
    RecyclerView.Adapter<PokemonAdapter.ItemViewHolder>() {

    interface PokemonListener {
        fun onPokemonClick(pokemon: PokemonResponse)
    }

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val adapterContainer =
            itemView.findViewById<ConstraintLayout>(R.id.adapterContainer)
        private val pokemonName = itemView.findViewById<TextView>(R.id.pokemonName)

        fun bind(pokemon: PokemonResponse, listener: PokemonListener) {
            pokemonName.text = pokemon.name
            adapterContainer.setOnClickListener { listener.onPokemonClick(pokemon) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ItemViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.pokemon_adapter, parent, false)
    )

    override fun getItemCount() = pokemons.size

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) =
        holder.bind(pokemons[position], listener)

    fun updateContent(newPokemonResponses: List<PokemonResponse>) {
        pokemons = newPokemonResponses
        notifyDataSetChanged()
    }
}