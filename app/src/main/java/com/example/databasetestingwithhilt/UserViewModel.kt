package com.example.databasetestingwithhilt

import android.gesture.GestureLibrary
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.ReportDrawn
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.databasetestingwithhilt.Authentications.AuthRepository
import com.example.databasetestingwithhilt.Database.FoodEntity
import com.example.databasetestingwithhilt.Database.NutrientRepository
import com.example.databasetestingwithhilt.Database.PersonalEntity
import com.example.databasetestingwithhilt.NutritionScreen.NutrientRequest
import com.example.databasetestingwithhilt.NutritionScreen.NutritionixApiObject
import com.example.databasetestingwithhilt.NutritionScreen.NutritionixResponse
import com.example.databasetestingwithhilt.SearchScreen.FoodItem
import com.example.databasetestingwithhilt.SearchScreen.SearchApiObject
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject


@HiltViewModel
class UserViewModel @Inject constructor(
    val repository: NutrientRepository,
    val authRepository: AuthRepository
): ViewModel() {

    // for search suggestions
    val searchResults = MutableStateFlow<List<FoodItem>>(emptyList())
    val errorMessage = mutableStateOf<String?>(null)
    val foodSuggestions: StateFlow<List<FoodItem>> = searchResults

    // for nutritents
    private val _foods = MutableStateFlow<List<NutritionixResponse.Food>>(emptyList())
    val foods: StateFlow<List<NutritionixResponse.Food>> = _foods
    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error
    // for live calories
    private val _liveCalorieCount = MutableStateFlow(0f)
    val liveCalorieCount : StateFlow<Float> = _liveCalorieCount.asStateFlow()
    // for live proteins
    private val _liveProteinCount = MutableStateFlow(0f)
    val liveProteinCount : StateFlow<Float> = _liveProteinCount.asStateFlow()
    // for live carbs
    private val _liveCarbsCount = MutableStateFlow(0f)
    val liveCarbsCount : StateFlow<Float> = _liveCarbsCount.asStateFlow()
    // for live fats
    private val _liveFatsCount = MutableStateFlow(0f)
    val liveFatsCount: StateFlow<Float> = _liveFatsCount.asStateFlow()
    // for live trans fat
    private val _liveTransFatCount = MutableStateFlow(0f)
    val liveTransfatCount: StateFlow<Float> = _liveTransFatCount.asStateFlow()
    // for vitaminA
    private val _liveVitaminACount= MutableStateFlow(0f)
    val liveVitaminACount: StateFlow<Float> = _liveVitaminACount.asStateFlow()
    // for vitamin b6
    private val _liveVitaminB6Count = MutableStateFlow(0f)
    val liveVitaminB6Count: StateFlow<Float> = _liveVitaminB6Count.asStateFlow()
    // for vitamin b12
    private val _liveVitaminB12Count = MutableStateFlow(0f)
    val liveVitaminB12Count:StateFlow<Float> =_liveVitaminB12Count.asStateFlow()
    // for vitamin c
    private val _liveVitaminCCount = MutableStateFlow(0f)
    val liveVitaminCCount : StateFlow<Float> = _liveVitaminCCount.asStateFlow()
    // for vitamin D
    private val _liveVitaminDCount = MutableStateFlow(0f)
    val liveVitaminDCount:StateFlow<Float> =_liveVitaminDCount.asStateFlow()
    // for vitamin E
    private val _liveVitaminECount = MutableStateFlow(0f)
    val liveVitaminECount:StateFlow<Float> =_liveVitaminECount.asStateFlow()
    // for vitamin K
    private val _liveVitaminKCount = MutableStateFlow(0f)
    val liveVitaminKCount:StateFlow<Float> = _liveVitaminKCount.asStateFlow()
    // for copper
    private val _liveCopperCount = MutableStateFlow(0f)
    val liveCopperCount: StateFlow<Float> = _liveCopperCount.asStateFlow()
    // for zinc
    private val _liveZincCount = MutableStateFlow(0f)
    val liveZincCount: StateFlow<Float> =_liveZincCount.asStateFlow()
    // for sodium
    private val _liveSodiumCount = MutableStateFlow(0f)
    val liveSodiumCount: StateFlow<Float> = _liveSodiumCount.asStateFlow()
    // for potassium
    private val _livePotassiumCount = MutableStateFlow(0f)
    val livePotassiumCount: StateFlow<Float> =_livePotassiumCount.asStateFlow()
    // for Iron
    private val _liveIronCount = MutableStateFlow(0f)
    val liveIronCount:StateFlow<Float> = _liveIronCount.asStateFlow()
    // for calcium
    private val _liveCalciumCount = MutableStateFlow(0f)
    val liveCalcuimCount:StateFlow<Float> =_liveCalciumCount.asStateFlow()
    // for fibar
    private val _liveFibarCount = MutableStateFlow(0f)
    val liveFibarCount:StateFlow<Float> =_liveFibarCount.asStateFlow()
    // for suger
    private val _liveSugerCount = MutableStateFlow(0f)
    val liveSugerCount:StateFlow<Float> =_liveSugerCount.asStateFlow()
    // for water
    private val _liveWaterCount = MutableStateFlow(0f)
    val liveWaterCount:StateFlow<Float> = _liveWaterCount.asStateFlow()
    //for glucode
    private val _liveGlucoseCount = MutableStateFlow(0f)
    val liveGlucoseCount: StateFlow<Float> = _liveGlucoseCount.asStateFlow()
    // for Folic acid
    private val _liveFolicAcidCount = MutableStateFlow(0f)
    val liveFolicAcidCount:StateFlow<Float> = _liveFolicAcidCount.asStateFlow()
    // for Niacin
    private val _liveNiacinCount = MutableStateFlow(0f)
    val liveNiacinCount:StateFlow<Float>  = _liveNiacinCount.asStateFlow()
    // for Retinol
    private val _liveRetinolCount = MutableStateFlow(0f)
    val liveRetinolCount:StateFlow<Float> = _liveRetinolCount.asStateFlow()
    // for magnesium
    private val _liveMagnesiumCount = MutableStateFlow(0f)
    val liveMagnesiumCount:StateFlow<Float> = _liveMagnesiumCount
    // for folate
    private val _liveFolateCount = MutableStateFlow(0f)
    val liveFolateCount:StateFlow<Float> = _liveFolateCount.asStateFlow()
    // for cholesterol
    private val _liveCholesterolCount =MutableStateFlow(0f)
    val liveCholesteralCount:StateFlow<Float> = _liveCholesterolCount.asStateFlow()
    // for monosaturated fat
    private val _liveMonosaturateFatCount = MutableStateFlow(0f)
    val liveMonosaturatedFatCount:StateFlow<Float> = _liveMonosaturateFatCount.asStateFlow()
    // for polysaturate count
    private val _livePolysauratedFatCount = MutableStateFlow(0f)
    val livePolysaturatedFatCount:StateFlow<Float> = _livePolysauratedFatCount.asStateFlow()

    private val _foodNames=MutableStateFlow<List<String>>(emptyList())
    val foodNames:StateFlow<List<String>> = _foodNames

    private var searchJob: Job? = null

    val requiredcaloriecount = MutableStateFlow(0)
    val requiredproteincount = MutableStateFlow(0)
    val requiredfatscount = MutableStateFlow(0)
    val requiredcarbscount= MutableStateFlow(0)

    val nutrientMapping = mapOf(
        203 to "Protein",
        204 to "Total lipid (fat)",
        205 to "Carbohydrate",
        207 to "Ash",
        208 to "Energy",
        209 to "Starch",
        210 to "Sucrose",
        211 to "Glucose",
        212 to "Fructose",
        213 to "Lactose",
        214 to "Maltose",
        221 to "Alcohol",
        255 to "Water",
        262 to "Caffeine",
        263 to "Theobromine",
        268 to "Energy",
        269 to "Sugar",
        291 to "Fiber",
        301 to "Calcium",
        303 to "Iron",
        304 to "Magnesium",
        305 to "Phosphorus",
        306 to "Potassium",
        307 to "Sodium",
        309 to "Zinc",
        312 to "Copper",
        313 to "Fluoride",
        315 to "Manganese",
        317 to "Selenium",
        318 to "Vitamin A",
        319 to "Retinol",
        320 to "Vitamin A",
        321 to "Beta Carotene",
        322 to "Alpha Carotene",
        323 to "Vitamin E",
        324 to "Vitamin D (D2 + D3)",
        328 to "Vitamin D",
        334 to "Beta Cryptoxanthin",
        337 to "Lycopene",
        338 to "Lutein + zeaxanthin",
        341 to "Gamma Tocopherol",
        342 to "Delta Tocopherol",
        343 to "Beta Tocopherol",
        401 to "Vitamin C",
        404 to "Thiamin",
        405 to "Riboflavin",
        406 to "Niacin",
        410 to "Pantothenic acid",
        415 to "Vitamin B-6",
        417 to "Folate",
        418 to "Vitamin B-12",
        421 to "Choline",
        429 to "Menaquinone-4",
        430 to "Vitamin K (phylloquinone)",
        431 to "Folate",
        432 to "Folate, DFE",
        435 to "Folate, DFE",
        454 to "Folic acid",
        501 to "Tryptophan",
        502 to "Threonine",
        503 to "Isoleucine",
        504 to "Leucine",
        505 to "Lysine",
        506 to "Methionine",
        507 to "Cystine",
        508 to "Phenylalanine",
        509 to "Tyrosine",
        510 to "Valine",
        511 to "Arginine",
        512 to "Histidine",
        513 to "Alanine",
        514 to "Aspartic acid",
        515 to "Glutamic acid",
        516 to "Glycine",
        517 to "Proline",
        518 to "Serine",
        601 to "Cholesterol",
        605 to "Trans Fatty acids",
        606 to "Saturated Fatty acids",
        607 to "Butyric acid",
        608 to "Caproic acid",
        609 to "Caprylic acid",
        610 to "Capric acid",
        611 to "Lauric acid",
        612 to "Myristic acid",
        613 to "Palmitic acid",
        614 to "Stearic acid",
        617 to "Oleic acid",
        618 to "Linoleic acid",
        619 to "Alpha-Linolenic acid",
        620 to "Arachidonic acid",
        621 to "Docosahexaenoic acid, DHA",
        626 to "Myristoleic acid",
        627 to "Palmitoleic acid",
        628 to "cis-Vaccenic acid",
        629 to "Linoleic acid",
        630 to "Alpha-Linolenic acid",
        631 to "Eicosenoic acid",
        636 to "Docosahexaenoic acid",
        645 to "Monosaturated Fatty acids",
        646 to "Polysaturated Fatty acids"
    )

    // for Authentication
    private val _authState = MutableStateFlow<FirebaseUser?>(null)
    val authState: StateFlow<FirebaseUser?> = _authState

    private val _autherror = MutableStateFlow<String?>(null)
    val autherror: StateFlow<String?> =_autherror


    fun insertPersonalData(personalEntity: PersonalEntity){
        viewModelScope.launch {
            repository.insertPersonalData(personalEntity)
        }
    }

    // to get search result
    fun fetchSearchResult(query: String) {
        searchJob?.cancel()

        searchJob = viewModelScope.launch(Dispatchers.IO) {
            if (query.isNotEmpty()) {
                try {
                    errorMessage.value = null
                    val response = SearchApiObject.api.getSearchResults(query)
                    searchResults.value = response.common
                } catch (e: HttpException) {
                    val errorBody = e.response()?.errorBody()?.string()
                    errorMessage.value = "HTTP Error: ${e.code()} - ${errorBody ?: e.message}"
                } catch (e: Exception) {
                    errorMessage.value = "Error: ${e.message}"
                }
            } else {
                searchResults.value = emptyList()
            }
        }
    }

    fun getLiveCalorieCount(){
        viewModelScope.launch {
            val totalCalories= repository.getTotalCalories()?.toFloat() ?:0f
            _liveCalorieCount.value=totalCalories
        //    Log.d("Requirement", "getLiveCalorieCount: $totalCalories")
        }
    }

    fun getLiveProteinCount(){
        viewModelScope.launch {
            val totalProteins =repository.getTotalProtein()?.toFloat()?:0f
            _liveProteinCount.value=totalProteins
         //   Log.d("Requirement", "getLiveProteinCount: $totalProteins")
        }
    }

    fun getLiveCarbsCount(){
        viewModelScope.launch {
            val totalCarbs = repository.getTotalCarbs()?.toFloat()?:0f
            _liveCarbsCount.value=totalCarbs
        //    Log.d("Requirement", "getLiveCarbsCount:$totalCarbs ")

        }
    }

    fun getLiveFatsCount(){
        viewModelScope.launch {
            val totalFats = repository.getTotalFats()?.toFloat()?:0f
            _liveFatsCount.value=totalFats
      //      Log.d("Requirement", "getLiveFatCount: $totalFats")

        }
    }


    // to get nutrition
    fun fetchNutrients(query: NutrientRequest) {

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = NutritionixApiObject.API.getNutrients(query)
                _foods.value = response.foods
                Log.d("TAG", "fetchNutrients: ${_foods.value}")
                _error.value = null
            } catch (e: Exception) {
                _error.value = e.localizedMessage
            }
        }
    }

    fun logFood(foodName: String, nutrients: List<NutritionixResponse.Food.Nutrient>) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                // Create a mutable map to hold the nutrient values, defaulting to 0.0 for all nutrients
                val nutrientValues = nutrientMapping.keys.associateWith { 0.0 }.toMutableMap()

                // Populate the nutrient values from the list of nutrients
                nutrients.forEach { nutrient ->
                    val nutrientName = nutrientMapping[nutrient.attr_id]
                    if (nutrientName != null) {
                        nutrientValues[nutrient.attr_id] = nutrient.value.toDouble()
                    }
                }

                // Create a FoodEntity object
                val foodEntity = FoodEntity(
                    food_name = foodName,
                    Protein = nutrientValues[203] ?: 0.0,
                    Total_lipid_fat = nutrientValues[204] ?: 0.0,
                    Carbohydrate = nutrientValues[205] ?: 0.0,
                    Ash = nutrientValues[207] ?: 0.0,
                    Energy = nutrientValues[208] ?: 0.0,
                    Starch = nutrientValues[209] ?: 0.0,
                    Sucrose = nutrientValues[210] ?: 0.0,
                    Glucose = nutrientValues[211] ?: 0.0,
                    Fructose = nutrientValues[212] ?: 0.0,
                    Lactose = nutrientValues[213] ?: 0.0,
                    Maltose = nutrientValues[214] ?: 0.0,
                    Alcohol = nutrientValues[221] ?: 0.0,
                    Wate = nutrientValues[255] ?: 0.0,
                    Caffeine = nutrientValues[262] ?: 0.0,
                    Theobromine = nutrientValues[263] ?: 0.0,
                    Sugar = nutrientValues[269] ?: 0.0,
                    Fiber = nutrientValues[291] ?: 0.0,
                    Calcium = nutrientValues[301] ?: 0.0,
                    Iron = nutrientValues[303] ?: 0.0,
                    Magnesium = nutrientValues[304] ?: 0.0,
                    Phosphorus = nutrientValues[305] ?: 0.0,
                    Potassium = nutrientValues[306] ?: 0.0,
                    Sodium = nutrientValues[307] ?: 0.0,
                    Zinc = nutrientValues[309] ?: 0.0,
                    Copper = nutrientValues[312] ?: 0.0,
                    Fluoride = nutrientValues[313] ?: 0.0,
                    Manganese = nutrientValues[315] ?: 0.0,
                    Selenium = nutrientValues[317] ?: 0.0,
                    Vitamin_A = nutrientValues[318] ?: 0.0,
                    Retinol = nutrientValues[319] ?: 0.0,
                    Beta_Carotene = nutrientValues[321] ?: 0.0,
                    Alpha_Carotene = nutrientValues[322] ?: 0.0,
                    Vitamin_E = nutrientValues[323] ?: 0.0,
                    Vitamin_D = nutrientValues[324] ?: 0.0,
                    Beta_Cryptoxanthin = nutrientValues[334] ?: 0.0,
                    Lycopene = nutrientValues[337] ?: 0.0,
                    Lutein_zeaxanthin = nutrientValues[338] ?: 0.0,
                    Gamma_Tocopherol = nutrientValues[341] ?: 0.0,
                    Delta_Tocopherol = nutrientValues[342] ?: 0.0,
                    Beta_Tocopherol = nutrientValues[343] ?: 0.0,
                    Vitamin_C = nutrientValues[401] ?: 0.0,
                    Thiamin = nutrientValues[404] ?: 0.0,
                    Riboflavin = nutrientValues[405] ?: 0.0,
                    Niacin = nutrientValues[406] ?: 0.0,
                    Pantothenic_acid = nutrientValues[410] ?: 0.0,
                    Vitamin_B6 = nutrientValues[415] ?: 0.0,
                    Folate = nutrientValues[417] ?: 0.0,
                    Vitamin_B12 = nutrientValues[418] ?: 0.0,
                    Choline = nutrientValues[421] ?: 0.0,
                    Menaquinone4 = nutrientValues[429] ?: 0.0,
                    Vitamin_K = nutrientValues[430] ?: 0.0,
                    Folic_acid = nutrientValues[454] ?: 0.0,
                    Tryptophan = nutrientValues[501] ?: 0.0,
                    Threonine = nutrientValues[502] ?: 0.0,
                    Isoleucine = nutrientValues[503] ?: 0.0,
                    Leucine = nutrientValues[504] ?: 0.0,
                    Lysine = nutrientValues[505] ?: 0.0,
                    Methionine = nutrientValues[506] ?: 0.0,
                    Cystine = nutrientValues[507] ?: 0.0,
                    Phenylalanine = nutrientValues[508] ?: 0.0,
                    Tyrosine = nutrientValues[509] ?: 0.0,
                    Valine = nutrientValues[510] ?: 0.0,
                    Arginine = nutrientValues[511] ?: 0.0,
                    Histidine = nutrientValues[512] ?: 0.0,
                    Alanine = nutrientValues[513] ?: 0.0,
                    Aspartic_acid = nutrientValues[514] ?: 0.0,
                    Glutamic_acid = nutrientValues[515] ?: 0.0,
                    Glycine = nutrientValues[516] ?: 0.0,
                    Proline = nutrientValues[517] ?: 0.0,
                    Serine = nutrientValues[518] ?: 0.0,
                    Cholesterol = nutrientValues[601] ?: 0.0,
                    Trans_Fatty_acids = nutrientValues[605] ?: 0.0,
                    Saturated_Fatty_acids = nutrientValues[606] ?: 0.0,
                    Butyric_acid = nutrientValues[607] ?: 0.0,
                    Caproic_acid = nutrientValues[608] ?: 0.0,
                    Caprylic_acid = nutrientValues[609] ?: 0.0,
                    Capric_acid = nutrientValues[610] ?: 0.0,
                    Lauric_acid = nutrientValues[611] ?: 0.0,
                    Myristic_acid = nutrientValues[612] ?: 0.0,
                    Palmitic_acid = nutrientValues[613] ?: 0.0,
                    Stearic_acid = nutrientValues[614] ?: 0.0,
                    Oleic_acid = nutrientValues[617] ?: 0.0,
                    Linoleica_acid = nutrientValues[618] ?: 0.0,
                    Alpha_Linolenic_acid = nutrientValues[619] ?: 0.0,
                    Arachidonic_acid = nutrientValues[620] ?: 0.0,
                    DHA = nutrientValues[621] ?: 0.0,
                    Myristoleic_acid = nutrientValues[626] ?: 0.0,
                    Palmitoleic_acid = nutrientValues[627] ?: 0.0,
                    cis_Vaccenic_acid = nutrientValues[628] ?: 0.0,
                    Linoleic_acid = nutrientValues[629] ?: 0.0,
                    Eicosenoic_acid = nutrientValues[630] ?: 0.0,
                    Docosahexaenoic_acid = nutrientValues[631] ?: 0.0,
                    Monosaturated_Fatty_acids = nutrientValues[645] ?: 0.0,
                    Polysaturated_Fatty_acids = nutrientValues[646] ?: 0.0
                )

                // Insert into the database
                repository.insertFood(foodEntity)
            } catch (e: Exception) {
                _error.value = "Error logging food: ${e.message}"
            }
        }
    }



    fun login(email : String, password: String){
        viewModelScope.launch {
            authRepository.login(email,password).addOnCompleteListener{ task ->
                if (task.isSuccessful){
                    _authState.value = authRepository.firebaseAuth.currentUser

                }else{
                    _autherror.value = task.exception?.message
                }

            }
        }
    }

    fun register(email: String, password: String){
        viewModelScope.launch {
            authRepository.register(email,password).addOnCompleteListener { task ->
                if (task.isSuccessful){
                    _authState.value= authRepository.firebaseAuth.currentUser
                }else{
                    _autherror.value = task.exception?.message

                }

            }
        }
    }

    fun logout(){
        authRepository.logout()
        _authState.value=null
    }

    fun getRequiredCalories(){
        viewModelScope.launch {
            val _requiredcalories =repository.getRequiredCalories()?.toInt() ?:0
           requiredcaloriecount.value=_requiredcalories
          // Log.d("Requirement", "getRequiredCalories: $_requiredcalories")
        }
    }

    fun getRequiredProteins(){
        viewModelScope.launch {
            val _requiredprotein = repository.getRequiredProteins()?.toInt()?:0
            requiredproteincount.value=_requiredprotein
         //   Log.d("Requirement", "getRequiredProteins: $_requiredprotein")
        }
    }

    fun getRequiredFats(){
        viewModelScope.launch {
            val _requiredFats = repository.getRequiredFats()?.toInt()?:0
            requiredfatscount.value=_requiredFats
        //    Log.d("Requirement", "getRequiredFats: $_requiredFats")
        }
    }

    fun getRequiredCarbs(){
        viewModelScope.launch {
            val _requiredCarbs = repository.getRequiredCarbs()?.toInt()?:0
            requiredcarbscount.value=_requiredCarbs
          //  Log.d("Requirement", "getRequiredCarbs: $_requiredCarbs")

        }
    }
    fun getAllFoodName(){
        viewModelScope.launch {
            val foodNamesFromDb = repository.getAllFoodName()
           // Log.d("FoodNames", "Fetched Food Names: $foodNamesFromDb")
            _foodNames.value=foodNamesFromDb
        }
    }
    // Add this method in the ViewModel
    fun removeFood(foodName: String) {
        val currentList = foodNames.value.toMutableList()
        currentList.remove(foodName)
        _foodNames.value = _foodNames.value.filter { it != foodName }
    }


    fun deleteFoodRecord(foodname : String){
        viewModelScope.launch {
            repository.deleteFoodRecord(foodname)
        }
    }
    fun getTransFatCount(){
        viewModelScope.launch {
            val TransFat = repository.getTransFat()
            _liveTransFatCount.value=TransFat
        }
    }
    fun getVitaminACount(){
        viewModelScope.launch {
            val VitaminA = repository.getVitaminA()
            _liveVitaminACount.value=VitaminA
        }
    }
    fun getVitaminB6(){
        viewModelScope.launch {
            val VitaminB6 = repository.getVitaminB6()
            _liveVitaminB6Count.value=VitaminB6
        }
    }
    fun getVitaminB12(){
        viewModelScope.launch {
            val VitaminB12 = repository.getVitaminB12()
            _liveVitaminB12Count.value=VitaminB12
        }
    }
    fun getVitaminCCount(){
        viewModelScope.launch {
            val VitaminC = repository.getVitaminC()
            _liveVitaminCCount.value=VitaminC
        }
    }
    fun getVitaminD(){
        viewModelScope.launch {
            val VitaminD= repository.getVitaminD()
            _liveVitaminDCount.value=VitaminD
        }
    }
    fun getVitaminE(){
        viewModelScope.launch {
            val VitaminE = repository.getVitaminE()
            _liveVitaminECount.value=VitaminE
        }
    }
    fun getVitaminK(){
        viewModelScope.launch {
            val VitaminK = repository.getVitaminK()
            _liveVitaminKCount.value=VitaminK
        }
    }
    fun getCopper(){
        viewModelScope.launch {
            val Copper = repository.getCopper()
            _liveCopperCount.value=Copper
        }
    }
    fun getZinc(){
        viewModelScope.launch {
            val Zinc = repository.getZinc()
            _liveZincCount.value=Zinc
        }
    }
    fun getSodium(){
        viewModelScope.launch {
            val Sodium = repository.getSodium()
            _liveSodiumCount.value=Sodium
        }
    }
    fun getPotassium(){
        viewModelScope.launch {
            val Potassium = repository.getPotassium()
            _livePotassiumCount.value=Potassium
        }
    }
    fun getIron(){
        viewModelScope.launch {
            val Iron = repository.getIron()
            _liveIronCount.value=Iron
        }
    }
    fun getCalcium(){
        viewModelScope.launch {
            val Calcium = repository.getCalcium()
            _liveCalciumCount.value= Calcium
        }
    }
    fun getFibar(){
        viewModelScope.launch {
            val Fibar = repository.getFibar()
            _liveFibarCount.value=Fibar
        }
    }
    fun getSuger(){
        viewModelScope.launch {
            val Suger = repository.getSuger()
            _liveSugerCount.value=Suger
        }
    }
    fun getWater(){
        viewModelScope.launch {
            val water = repository.getWater()
            _liveWaterCount.value= water
        }
    }
    fun getGlucose(){
        viewModelScope.launch {
            val Glucose= repository.getGlucose()
            _liveGlucoseCount.value=Glucose
        }
    }
    fun getFolicAcid(){
        viewModelScope.launch {
            val FolicAcid = repository.getFolicAcid()
            _liveFolicAcidCount.value=FolicAcid
        }
    }
    fun getNiacin(){
        viewModelScope.launch {
            val Niacin = repository.getNiacine()
            _liveNiacinCount.value=Niacin
        }
    }
    fun getRetinol(){
        viewModelScope.launch {
            val Retinol = repository.getRetinol()
            _liveRetinolCount.value=Retinol
        }
    }
    fun getMagnesium(){
        viewModelScope.launch {
            val Magnesium = repository.getMagnesium()
            _liveMagnesiumCount.value=Magnesium
        }
    }
    fun getFolate(){
        viewModelScope.launch {
            val Folate= repository.getFolate()
            _liveFolateCount.value=Folate
        }
    }
    fun getCholesterol(){
        viewModelScope.launch {
            val Cholesterol = repository.getCholestrol()
            _liveCholesterolCount.value=Cholesterol
        }
    }
    fun getMonosaturatedFatCount(){
        viewModelScope.launch {
            val MonosaturatedFat = repository.getMonosaturatedFat()
            _liveMonosaturateFatCount.value=MonosaturatedFat
        }
    }
    fun getPolysaturatedFatCount(){
        viewModelScope.launch {
            val PolysaturatedCount = repository.getPolysatiratedFat()
            _livePolysauratedFatCount.value=PolysaturatedCount
        }
    }
}