package com.example.pokedexdesafio.data.services

import android.util.Log
import com.example.pokedexdesafio.core.functional.Failure
import com.example.pokedexdesafio.core.functional.Response
import com.example.pokedexdesafio.core.functional.Success
import com.example.pokedexdesafio.core.utils.HTTP_400
import com.example.pokedexdesafio.core.utils.HTTP_500
import com.example.pokedexdesafio.data.api.PokemonApi
import com.example.pokedexdesafio.data.model.toAbility
import com.example.pokedexdesafio.data.model.toMove
import com.example.pokedexdesafio.data.model.toType
import com.example.pokedexdesafio.domain.model.PokemonDetail
import retrofit2.Retrofit
import javax.inject.Inject

class GetPokemonDetailService @Inject constructor(private val retrofit: Retrofit) {

    fun fetchPokemonDetails(id: String): Response<PokemonDetail> {
        return try {
            val httpResponse =
                retrofit.create(PokemonApi::class.java).fetchPokemonDetails(id).execute()

            if (httpResponse.isSuccessful) {
                httpResponse.body()?.let {
                    val pokemonDetail = PokemonDetail(
                        it.id,
                        it.name,
                        it.types?.mapNotNull { value -> value.type?.toType() }.orEmpty(),
                        it.moves?.mapNotNull { value -> value.move?.toMove() }.orEmpty(),
                        it.abilities?.mapNotNull { value -> value.ability?.toAbility() }.orEmpty(),
                        it.locationAreaEncounters
                    )
                    Response(Success(pokemonDetail))
                } ?: Response(failure = Failure.GenericFailure)
            } else {
                when (httpResponse.code()) {
                    in HTTP_400 -> Response(failure = Failure.ServerNotFound)
                    in HTTP_500 -> Response(failure =Failure.ServerError)
                    else -> Response(failure = Failure.NetworkError)
                }
            }
        } catch (exception: Exception) {
            Log.e(javaClass.name, exception.toString())
            Response(failure = Failure.NetworkError)
        }
    }
}