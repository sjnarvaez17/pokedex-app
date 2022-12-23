package com.example.pokedexdesafio.data.services

import android.util.Log
import com.example.pokedexdesafio.core.functional.Failure
import com.example.pokedexdesafio.core.functional.Response
import com.example.pokedexdesafio.core.functional.Success
import com.example.pokedexdesafio.core.utils.HTTP_400
import com.example.pokedexdesafio.core.utils.HTTP_500
import com.example.pokedexdesafio.data.api.PokemonApi
import com.example.pokedexdesafio.data.model.toPokemonList
import com.example.pokedexdesafio.domain.model.Pokemon
import retrofit2.Retrofit
import javax.inject.Inject

class GetPokemonService @Inject constructor(private val retrofit: Retrofit) {

    fun fetchPokemonList(): Response<List<Pokemon>> {
        return try {
            val httpResponse = retrofit.create(PokemonApi::class.java).fetchPokemonList().execute()

            if (httpResponse.isSuccessful) {
                val value = httpResponse.body()?.toPokemonList().orEmpty()
                Response(Success(value), null)
            } else {
                when (httpResponse.code()) {
                    in HTTP_400 -> Response(null, Failure.ServerNotFound)
                    in HTTP_500 -> Response(null, Failure.ServerError)
                    else -> Response(null, Failure.NetworkError)
                }
            }
        } catch (exception: Exception) {
            Log.e(javaClass.name, exception.toString())
            Response(null, Failure.NetworkError)
        }
    }
}