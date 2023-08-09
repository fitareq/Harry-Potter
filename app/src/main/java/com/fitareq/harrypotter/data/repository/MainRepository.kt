package com.fitareq.harrypotter.data.repository

import com.fitareq.harrypotter.data.models.Data
import com.fitareq.harrypotter.data.network.ApiService
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun getAllCharacter():Data{
        return try {
            val response = apiService.getAllCharacter()
            Data.Success(response)
        }catch (e:Exception){
            e.message?.let {
                if (it.contains("Unable to resolve host"))
                    Data.Error("No Internet")
            }
            Data.Error("Unexpected error! please try again")
        }
    }
}