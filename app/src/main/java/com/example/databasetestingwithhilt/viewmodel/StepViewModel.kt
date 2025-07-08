package com.example.databasetestingwithhilt.viewmodel

import androidx.lifecycle.ViewModel
import com.example.databasetestingwithhilt.repository.StepRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class StepViewModel @Inject constructor(
    val repository: StepRepository
):ViewModel(){

    val steps:StateFlow<Int> = repository.stepCount
}