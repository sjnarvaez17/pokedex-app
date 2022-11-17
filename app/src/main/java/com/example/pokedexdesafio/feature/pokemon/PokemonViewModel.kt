package com.example.pokedexdesafio.feature.pokemon

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import retrofit2.Response
import javax.inject.Inject

class PokemonViewModel @Inject constructor(private val pokemonUseCase: PokemonUseCase) :
    ViewModel() {

    private val disposable = CompositeDisposable()
    var pokemonList = MutableLiveData<Response<List<Pokemon>>>()
    var failure = MutableLiveData<Boolean>()

    fun fetchPokemonList() {
        disposable.add(
            pokemonUseCase().observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { pokemonList.postValue(it) },
                    { failure.postValue(true) }
                )
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.dispose()
    }
}