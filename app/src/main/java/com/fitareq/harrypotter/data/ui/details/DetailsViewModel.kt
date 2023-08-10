package com.fitareq.harrypotter.data.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fitareq.harrypotter.data.models.Data
import com.fitareq.harrypotter.data.repository.DetailsRepository
import com.fitareq.harrypotter.data.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val repository: DetailsRepository
): ViewModel() {
    private val _character = MutableLiveData<Data>()
    val character: LiveData<Data> = _character

    fun getCharacter(id:String){
        _character.postValue(Data.Loading)

        viewModelScope.launch(Dispatchers.IO) {
            repository.getCharacter(id).let {
                _character.postValue(it)
            }
        }

    }
}