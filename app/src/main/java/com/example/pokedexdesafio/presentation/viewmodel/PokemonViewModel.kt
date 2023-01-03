package com.example.pokedexdesafio.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.pokedexdesafio.core.functional.Response
import com.example.pokedexdesafio.core.platform.BaseViewModel
import com.example.pokedexdesafio.domain.model.Pokemon
import com.example.pokedexdesafio.domain.use_case.PokemonUseCase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import javax.inject.Inject

class PokemonViewModel @Inject constructor(private val pokemonUseCase: PokemonUseCase) :
    BaseViewModel() {

    var pokemonList = MutableLiveData<Response<List<Pokemon>>>()

    fun fetchPokemonList() {
        disposable.add(
            pokemonUseCase().observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { pokemonList.postValue(it) },
                    { pokemonList.postValue(Response()) }
                )
        )
    }
}