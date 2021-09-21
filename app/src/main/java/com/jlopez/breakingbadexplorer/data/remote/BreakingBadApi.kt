package com.jlopez.breakingbadexplorer.data.remote

import com.jlopez.breakingbadexplorer.data.remote.responses.Characters
import com.jlopez.breakingbadexplorer.data.remote.responses.CharactersItem
import retrofit2.http.GET

interface BreakingBadApi {

    @GET("characters")
    suspend fun getCharacterList(): Characters

}