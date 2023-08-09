package com.fitareq.harrypotter.data.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fitareq.harrypotter.data.models.Data
import com.fitareq.harrypotter.data.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: MainRepository
): ViewModel() {
    private val _characters = MutableLiveData<Data>()
    val characters: LiveData<Data> = _characters

    fun getAllCharacters(){
        _characters.postValue(Data.Loading)

        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllCharacter().let {
                _characters.postValue(it)
            }
        }

    }
}