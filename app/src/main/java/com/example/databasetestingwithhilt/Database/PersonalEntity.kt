package com.example.databasetestingwithhilt.Database

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "PersonalData_table")
data class PersonalEntity(
    @PrimaryKey(autoGenerate = true) val id : Int =0,
    val age : Int,
    val gender : String,
    val weight : Float,
    val height : Float,
    val activityLevel : String,
    val exerciseFrequency : Int,
    val occupationType : String
)
