package com.example.pokedexdesafio.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.pokedexdesafio.core.functional.Response
import com.example.pokedexdesafio.core.platform.BaseViewModel
import com.example.pokedexdesafio.domain.model.PokemonDetail
import com.example.pokedexdesafio.domain.use_case.GetPokemonDetailUseCase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import javax.inject.Inject

class PokemonDetailViewModel
@Inject constructor(private val pokemonDetailUseCase: GetPokemonDetailUseCase) :
    BaseViewModel() {

    var pokemonDetails = MutableLiveData<Response<PokemonDetail>>()

    fun fetchPokemonDetails(pokemonId: String) {
        disposable.add(
            pokemonDetailUseCase(pokemonId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { pokemonDetails.postValue(it) },
                    { pokemonDetails.postValue(Response()) }
                )
        )
    }

}