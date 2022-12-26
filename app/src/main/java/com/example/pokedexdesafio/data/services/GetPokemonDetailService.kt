package com.example.pokedexdesafio.data.services

import android.util.Log
import com.example.pokedexdesafio.core.functional.Failure
import com.example.pokedexdesafio.core.functional.Response
import com.example.pokedexdesafio.core.functional.Success
import com.example.pokedexdesafio.core.utils.HTTP_400
import com.example.pokedexdesafio.core.utils.HTTP_500
import com.example.pokedexdesafio.data.api.PokemonApi
import com.example.pokedexdesafio.data.model.toAbilitiesList
import com.example.pokedexdesafio.data.model.toMovesList
import com.example.pokedexdesafio.data.model.toTypesList
import com.example.pokedexdesafio.domain.model.PokemonDetail
import retrofit2.Retrofit
import javax.inject.Inject

class GetPokemonDetailService @Inject constructor(private val retrofit: Retrofit) {

    fun fetchPokemonDetails(id: String): Response<PokemonDetail> {
        return try {
           val httpResponse = retrofit.create(PokemonApi::class.java).fetchPokemonDetails(id).execute()

            if (httpResponse.isSuccessful) {
                val listMoves = httpResponse.body()?.toMovesList().orEmpty()
                val listAbility = httpResponse.body()?.toAbilitiesList().orEmpty()
                val typeList = httpResponse.body()?.toTypesList().orEmpty()
                val pokemonDetail = PokemonDetail(
                    httpResponse.body()?.id,
                    httpResponse.body()?.name,
                    typeList,
                    listMoves,
                    listAbility,
                    httpResponse.body()?.locationAreaEncounters
                )
                Response(Success(pokemonDetail))
            }else {
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