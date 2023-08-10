package com.fitareq.harrypotter.data.network

import com.fitareq.harrypotter.data.models.Character
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("characters")
    suspend fun getAllCharacter():List<Character>

    @GET("character/{id}")
    suspend fun getCharacter(@Path("id") id: String):List<Character>
}