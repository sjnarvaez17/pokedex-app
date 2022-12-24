package com.example.pokedexdesafio.data.services

import android.util.Log
import com.example.pokedexdesafio.core.utils.ERROR_CODE_IO_EXCEPTION
import com.example.pokedexdesafio.core.utils.ERROR_CONTENT_LENGTH
import com.example.pokedexdesafio.core.utils.MEDIA_TYPE_JSON
import com.example.pokedexdesafio.data.api.PokemonApi
import com.example.pokedexdesafio.data.model.PokemonDetailResponse
import com.example.pokedexdesafio.domain.model.PokemonDetail
import okhttp3.MediaType
import okhttp3.ResponseBody
import okio.Buffer
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject

class GetPokemonDetailService @Inject constructor(private val retrofit: Retrofit) {

    fun fetchPokemonDetails(id: String): Response<PokemonDetail> {
        return try {
           retrofit.create(PokemonApi::class.java).fetchPokemonDetails(id).execute()
        } catch (exception: Exception) {
            Log.e(javaClass.name, exception.toString())
            Response.error(
                ERROR_CODE_IO_EXCEPTION,
                ResponseBody.create(
                    MediaType.get(MEDIA_TYPE_JSON),
                    ERROR_CONTENT_LENGTH.toLong(),
                    Buffer()
                )
            )
        }
    }
}