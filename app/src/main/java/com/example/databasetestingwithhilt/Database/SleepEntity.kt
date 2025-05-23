package com.example.databasetestingwithhilt.Database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Sleep_Table")
data class SleepEntity(
    @PrimaryKey(autoGenerate = true) val id : Int =0,
    val Date : String,
    val SleepTime : String,
    val WakeUp : String? = null
)
