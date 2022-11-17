package com.example.pokedexdesafio.feature.pokemon_detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import retrofit2.Response
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