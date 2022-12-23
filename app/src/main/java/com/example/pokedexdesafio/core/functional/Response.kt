package com.example.pokedexdesafio.core.functional

data class Response<T>(val success: Success<T>?, val failure: Failure?)