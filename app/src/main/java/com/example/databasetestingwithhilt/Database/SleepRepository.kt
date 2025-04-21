package com.example.databasetestingwithhilt.Database

import javax.inject.Inject

class SleepRepository @Inject constructor(
    private val sleepDao : SleepDao
) {

    suspend fun insertSleepRecord(sleepEntity: SleepEntity)
    = sleepDao.insertSleepRecord(sleepEntity)
}