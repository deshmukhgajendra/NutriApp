package com.example.databasetestingwithhilt.viewmodel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.databasetestingwithhilt.model.PersonalEntity
import com.example.databasetestingwithhilt.repository.FirebaseRepository
import com.example.databasetestingwithhilt.repository.NutrientRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class FirebaseViewmodel @Inject constructor(
    val firebaseRepository: FirebaseRepository,
    val nutrientRepository: NutrientRepository
):ViewModel() {

    private val _requiredCalorie = MutableStateFlow(0f)
    val requiredCalories = _requiredCalorie.asStateFlow()

    private val _requiredProtein = MutableStateFlow(0f)
    val requiredProtein = _requiredProtein.asStateFlow()

    private val _requiredFats = MutableStateFlow(0f)
    val requiredFats = _requiredFats.asStateFlow()

    private val _requiredCarbs = MutableStateFlow(0f)
    val requiredCarbs= _requiredCarbs.asStateFlow()

    private val _userEmail = MutableLiveData<String?>()
    val userEmail: LiveData<String?> = _userEmail

    private val _userName = MutableLiveData<String?>()
    val userName: LiveData<String?> = _userName

    private val _saveResult = MutableLiveData<Boolean>()
    val saveResult : LiveData<Boolean> get() = _saveResult

    private val _proteinValue = mutableStateOf(0f)
    val proteinValue: State<Float> = _proteinValue

    private val _errorprotein = mutableStateOf<String?>(null)
    val errorprotein: State<String?> = _errorprotein

    private val _fatsValue = mutableStateOf(0f)
    val fatsValue: State<Float> = _fatsValue

    private val _errorfats = mutableStateOf<String?>(null)
    val  errorFats : State<String?> = _errorfats

    private val _carbsValue = mutableStateOf(0f)
    val carbsValue: State<Float> = _carbsValue

    private val _errorcarbs = mutableStateOf<String?>(null)
    val  errorCarbs : State<String?> = _errorcarbs

    private val _caloriesValue = mutableStateOf(0f)
    val caloriesValue: State<Float?> = _caloriesValue

    private val _errorcalorie= mutableStateOf<String?>(null)
    val  errorCalorie : State<String?> = _errorcalorie

    private val _personalData= MutableStateFlow<Map<String, Any?>>(emptyMap())
    val personalData:StateFlow<Map<String, Any?>> = _personalData

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    var name by mutableStateOf("")
    var age by mutableStateOf(0)
    var gender by mutableStateOf("")
    var weight by mutableStateOf(0f)
    var height by mutableStateOf(0f)
    var goal by mutableStateOf("")
    var activityLevel by mutableStateOf("")
    var exerciseFrequency by mutableStateOf(0)
    var occupationType by mutableStateOf("")
    var RequiredCalorie by mutableStateOf(0f)
    var RequiredProtein by mutableStateOf(0f)
    var RequiredCarbs by mutableStateOf(0f)
    var RequiredFats by mutableStateOf(0f)


    init {
        getUserDetails()
    }
    fun saveUserData(personalEntity: PersonalEntity){
        firebaseRepository.saveUserData(personalEntity){sucess ->
            _saveResult.value =sucess
            Log.d("userdata", "saveUserData:${saveResult.value} ")
        }
    }

    fun fetchRequiredCalories(){
        viewModelScope.launch {
            _requiredCalorie.value=firebaseRepository.getRequiredCalories()
        }
    }

    fun fetchRequiredNutrients(){
        viewModelScope.launch {
            _requiredCalorie.value=firebaseRepository.getRequiredCalories()
            _requiredProtein.value=firebaseRepository.getRequiredProtein()
            _requiredCarbs.value=firebaseRepository.getRequiredCarbs()
            _requiredFats.value=firebaseRepository.getRequiredFats()
        }
    }

    fun fetchProtein(date: String) {
        viewModelScope.launch {
            _errorprotein.value = null
            try {
                _proteinValue.value = firebaseRepository.getProteinValue(date)
            } catch (e: Exception) {
                _errorprotein.value = e.message
            }
        }
    }

    fun fetchFats(date : String){
        viewModelScope.launch {
            _errorfats.value = null

            try {
                _fatsValue.value = firebaseRepository.getFatsValue(date)
            }catch (e : Exception){
                _errorfats.value = e.message
            }
        }
    }
    fun fetchCarbs(date : String){
        viewModelScope.launch {
            _errorcarbs.value = null

            try {
                _carbsValue.value = firebaseRepository.getCarbsValue(date)
            }catch (e : Exception){
                _errorcarbs.value = e.message
            }
        }
    }
    fun fetchCalorie(date : String){
        viewModelScope.launch {
            _errorcalorie.value = null

            try {
                _caloriesValue.value = firebaseRepository.getCalorieValue(date)
            }catch (e : Exception){
                _errorcalorie.value = e.message
            }
        }
    }

//    fun fetchSleepData(date: String) {
//        viewModelScope.launch {
//            _isLoading.value = true
//            _error.value = null
//            try {
//                _sleepData.value = sleepRepository.getSleepData(date)
//                Log.d("SleepViewModel", "Fetched data: ${_sleepData.value}")
//            } catch (e: Exception) {
//                _error.value = "Failed to load data: ${e.message}"
//                Log.e("SleepViewModel", "Error", e)
//            } finally {
//                _isLoading.value = false
//            }
//        }
//    }


    fun getUserDetails(){
        viewModelScope.launch {
            firebaseRepository.getCurrentUserName{name ->
                _userName.value=name
            }
            launch {
                firebaseRepository.getPersonalData(

                    onSucess = {data ->
                        _personalData.value=data

                    },
                    onError = {erroemsg->
                        _error.value=erroemsg
                    }

                )
            }
          //  Log.d("authName", "getUserDetails: ${_userName.value}")
            //   Log.d("auth", "getUserDetails: ${_userEmail.value}")
        }
    }
}