package com.example.databasetestingwithhilt.Database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface SleepDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSleepRecord(sleepEntity: SleepEntity)

    @Query("SELECT * FROM Sleep_Table ORDER BY id DESC LIMIT 1")
    suspend fun getLastRecord(): SleepEntity?

    @Query("UPDATE Sleep_Table SET WakeUp= :wakeTime WHERE date= :date")
    suspend fun updateWakeTime(date : String, wakeTime: String)
}