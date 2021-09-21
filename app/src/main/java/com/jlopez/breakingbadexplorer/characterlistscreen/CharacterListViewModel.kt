package com.jlopez.breakingbadexplorer.characterlistscreen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jlopez.breakingbadexplorer.data.remote.BreakingBadApi
import com.jlopez.breakingbadexplorer.data.remote.responses.CharactersItem
import com.jlopez.breakingbadexplorer.model.CharacterListEntry
import com.jlopez.breakingbadexplorer.repository.CharacterRepository
import com.jlopez.breakingbadexplorer.util.Constants
import com.jlopez.breakingbadexplorer.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject
import javax.inject.Singleton

@HiltViewModel
class CharacterListViewModel @Inject constructor(
    private val repository: CharacterRepository
): ViewModel(){

    //var characterList = mutableStateOf<ArrayList<CharactersItem>>(ArrayList())
    var characterList: ArrayList<CharactersItem> = ArrayList()
    var loadError = mutableStateOf("")
    var isLoading = mutableStateOf(false)

    private var isSearchedStarting = true
    private var cachedCharacterList = ArrayList<CharactersItem>()
    var isSearching = mutableStateOf(false)


    init {
        characterPaginated()
    }
    fun characterPaginated() {
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
    fun searchPokemonList(query: String) {
        val listToSearch = if(isSearchedStarting) {
            characterList
        } else {
            cachedCharacterList
        }

        viewModelScope.launch(Dispatchers.Default) {
            if(query.isEmpty()) {
                characterList = cachedCharacterList
                isSearching.value = false
                isSearchedStarting = true
                return@launch
            }
            var results = listToSearch.filter {
                it.name.contains(query.trim(), ignoreCase = true) ||
                        it.char_id.toString() == query.trim()
            }
            if(isSearchedStarting) {
                cachedCharacterList = characterList
                isSearchedStarting = false
            }
            characterList = results.toCollection(ArrayList())
            isSearching.value = true
        }
    }


}