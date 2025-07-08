package com.example.databasetestingwithhilt.model

data class NutritionixResponse(
    val foods: List<Food>
) {
    data class Food(
        val food_name: String,
        val nf_calories: Float,
        val full_nutrients: List<Nutrient>
    ) {
        data class Nutrient(
            val attr_id: Int,
            val value: Float
        )
    }
}

data class NutrientRequest(
    val query: String
)