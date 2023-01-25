package com.amaurypm.pokemon.network

import com.amaurypm.pokemon.model.PokemonDetail
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Creado por Amaury Perea Matsumura el 20/01/23
 */
interface PokemonApi {

    //https://pokeapi.co/api/v2/pokemon/149/

    @GET("api/v2/pokemon/{id}")
    fun getPokemonDetail(
        @Path("id") id: String?
    ): Call<PokemonDetail>
}