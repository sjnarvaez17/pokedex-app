package com.example.pokedexdesafio.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pokedexdesafio.core.functional.Response
import com.example.pokedexdesafio.data.model.PokemonDetailResponse
import com.example.pokedexdesafio.domain.model.PokemonDetail
import com.example.pokedexdesafio.domain.use_case.GetPokemonDetailUseCase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import javax.inject.Inject

class PokemonDetailViewModel
@Inject constructor(private val pokemonDetailUseCase: GetPokemonDetailUseCase) :
    ViewModel() {

    private val disposable = CompositeDisposable()
    var pokemonDetails = MutableLiveData<Response<PokemonDetail>>()
    var failure = MutableLiveData<Boolean>()

    fun fetchPokemonDetails(pokemonId: String) {
        disposable.add(
            pokemonDetailUseCase(pokemonId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { pokemonDetails.value = it },
                    { failure.value = true }
                )
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.dispose()
    }
}