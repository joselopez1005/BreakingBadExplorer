package com.jlopez.breakingbadexplorer.repository

import com.jlopez.breakingbadexplorer.data.remote.BreakingBadApi
import com.jlopez.breakingbadexplorer.data.remote.responses.Characters
import com.jlopez.breakingbadexplorer.util.Resource
import dagger.hilt.android.scopes.ActivityScoped
import java.lang.Exception
import javax.inject.Inject

@ActivityScoped
class CharacterRepository @Inject constructor(
    private val api: BreakingBadApi
){

    suspend fun getCharacterList(): Resource<Characters>{
        val response = try {
            api.getCharacterList()
        } catch (e: Exception) {
            return Resource.Error("An unknown error occurred.")
        }
        return Resource.Success(response)
    }
}