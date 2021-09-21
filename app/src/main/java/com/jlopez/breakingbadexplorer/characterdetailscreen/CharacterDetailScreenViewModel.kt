package com.jlopez.breakingbadexplorer.characterdetailscreen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jlopez.breakingbadexplorer.data.remote.responses.CharactersItem
import com.jlopez.breakingbadexplorer.repository.CharacterRepository
import com.jlopez.breakingbadexplorer.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CharacterDetailScreenViewModel  @Inject constructor(
    private val repository: CharacterRepository) : ViewModel() {

    var characterList: ArrayList<CharactersItem> = ArrayList()
    var isLoading = mutableStateOf(false)
    var loadError = mutableStateOf("")



    init {
        getCharacterList()
    }

    private fun getCharacterList(){
        viewModelScope.launch {
            isLoading.value = true
            val result = repository.getCharacterList()
            when(result) {
                is Resource.Success -> {
                    for(entry in result.data!!) {
                        characterList.add(entry)
                    }
                    loadError.value = ""
                    isLoading.value = false
                }
                is Resource.Error -> {
                    loadError.value = result.message!!
                    isLoading.value = false
                }
            }
        }
    }


}