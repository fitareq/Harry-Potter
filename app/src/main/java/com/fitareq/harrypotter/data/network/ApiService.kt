package com.fitareq.harrypotter.data.network

import com.fitareq.harrypotter.data.models.Character
import retrofit2.http.GET

interface ApiService {
    @GET("characters")
    suspend fun getAllCharacter():List<Character>
}