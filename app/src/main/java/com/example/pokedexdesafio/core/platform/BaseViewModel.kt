package com.example.pokedexdesafio.core.platform

import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable

abstract class BaseViewModel : ViewModel() {

    val disposable = CompositeDisposable()

    override fun onCleared() {
        super.onCleared()
        disposable.dispose()
    }
}