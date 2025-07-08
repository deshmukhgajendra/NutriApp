package com.example.databasetestingwithhilt.data.remote

import com.example.databasetestingwithhilt.model.NutrientRequest
import com.example.databasetestingwithhilt.model.NutritionixResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST


interface NutritionInterface {

    @POST("v2/natural/nutrients")
    @Headers(
        "Content-Type: application/json",
        "x-app-id: 7012e6ae",
        "x-app-key: 16efec930d2eecbe7c620a7457f3eb3e"
    )
    suspend fun getNutrients(
        @Body query: NutrientRequest
    )
            : NutritionixResponse
}

object NutritionixApiObject{

    val base_url = "https://trackapi.nutritionix.com/"

    val API : NutritionInterface by lazy {
        Retrofit.Builder()
            .baseUrl(base_url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NutritionInterface::class.java)
    }
}