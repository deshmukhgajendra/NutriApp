package com.example.databasetestingwithhilt.model


data class SearchResponse(
    val common: List<FoodItem>
)

data class FoodItem(
    val food_name: String,
    val serving_qty: String
)
