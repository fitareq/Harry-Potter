package com.fitareq.harrypotter.data.models

sealed class Data {
    data class Success(val data: List<Character>) : Data()
    data class Error(val errorMessage: String) : Data()
    object Loading : Data()
}