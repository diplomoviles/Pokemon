package com.amaurypm.pokemon

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.amaurypm.pokemon.databinding.ActivityMainBinding
import com.amaurypm.pokemon.model.PokemonDetail
import com.amaurypm.pokemon.network.PokemonApi
import com.bumptech.glide.Glide
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        CoroutineScope(Dispatchers.IO).launch {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://pokeapi.co/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val call = retrofit.create(PokemonApi::class.java).getPokemonDetail("300")

            call.enqueue(object: Callback<PokemonDetail>{
                override fun onResponse(
                    call: Call<PokemonDetail>,
                    response: Response<PokemonDetail>
                ) {
                    Log.d("LOGS", "${response.body()?.sprites?.other?.official_artwork?.front_default}")
                    Log.d("LOGS", "${response.body()?.name}")

                    Glide.with(this@MainActivity)
                        .load("${response.body()?.sprites?.other?.official_artwork?.front_default}")
                        .into(binding.ivPokemon)

                    binding.tvPokemonName.text = "${response.body()?.name}".replaceFirstChar {it.uppercase()}

                }

                override fun onFailure(call: Call<PokemonDetail>, t: Throwable) {

                }

            })

        }
    }
}