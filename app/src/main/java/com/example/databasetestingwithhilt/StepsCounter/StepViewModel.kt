package com.example.databasetestingwithhilt.StepsCounter

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class StepViewModel @Inject constructor(
    val repository: StepRepository
):ViewModel(){

    val steps:StateFlow<Int> = repository.stepCount
}