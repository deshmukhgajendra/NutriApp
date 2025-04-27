package com.example.databasetestingwithhilt.Database

import javax.inject.Inject

class SleepRepository @Inject constructor(
    private val sleepDao : SleepDao
) {

    suspend fun insertSleepRecord(sleepEntity: SleepEntity)
    = sleepDao.insertSleepRecord(sleepEntity)

    suspend fun getLastSleepRecord() :SleepEntity?
    =sleepDao.getLastRecord()

    suspend fun updateWaketime(date:String , wakeTime: String)
    = sleepDao.updateWakeTime(date,wakeTime)
}