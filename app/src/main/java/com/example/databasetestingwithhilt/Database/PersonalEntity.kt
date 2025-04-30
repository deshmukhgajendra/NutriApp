package com.example.databasetestingwithhilt.Database

import androidx.room.Entity
import androidx.room.PrimaryKey


data class PersonalEntity(
    val age : Int,
    val gender : String,
    val weight : Float,
    val height : Float,
    val activityLevel : String,
    val exerciseFrequency : Int,
    val occupationType : String,
    val RequiredCalorie : Float,
    val RequiredProtein : Float,
    val RequiredCarbs : Float,
    val RequiredFats : Float
)
