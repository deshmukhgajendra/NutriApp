package com.example.databasetestingwithhilt.data.remote

import com.example.databasetestingwithhilt.model.SearchResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query


interface SearchFoodInterface {
    @GET("search/instant/")
    @Headers(
        "Content-Type: application/json",
        "x-app-id: 7012e6ae",
        "x-app-key: 16efec930d2eecbe7c620a7457f3eb3e"
    )
    suspend fun getSearchResults(
        @Query("query") query :String
    ): SearchResponse
}


object SearchApiObject {

    private const val BASE_URL = "https://trackapi.nutritionix.com/v2/"


    val api: SearchFoodInterface by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()) // Add Gson converter
            .build()
            .create(SearchFoodInterface::class.java) // Create API service
    }
}
