package com.example.databasetestingwithhilt.UiScreens.PersonalInformations

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.databasetestingwithhilt.model.PersonalEntity
import com.example.databasetestingwithhilt.R
import com.example.databasetestingwithhilt.UiScreens.MainActivity
import com.example.databasetestingwithhilt.UiScreens.PersonalInformations.screens.ActivityLevelScreen
import com.example.databasetestingwithhilt.UiScreens.PersonalInformations.screens.AgeandGenderScreen
import com.example.databasetestingwithhilt.UiScreens.PersonalInformations.screens.ExerciseFrequencyScreen
import com.example.databasetestingwithhilt.UiScreens.PersonalInformations.screens.GoalScreen
import com.example.databasetestingwithhilt.UiScreens.PersonalInformations.screens.HeightAndWeight
import com.example.databasetestingwithhilt.UiScreens.PersonalInformations.screens.NameScreen
import com.example.databasetestingwithhilt.UiScreens.PersonalInformations.screens.OccupationTypeScreen
import com.example.databasetestingwithhilt.viewmodel.UserViewModel
import com.example.databasetestingwithhilt.ui.theme.DatabaseTestingWithHiltTheme
import com.example.databasetestingwithhilt.ui.theme.OutFitFontFamily
import com.example.databasetestingwithhilt.ui.theme.fire
import com.example.databasetestingwithhilt.ui.theme.gray
import com.example.databasetestingwithhilt.viewmodel.FirebaseViewmodel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class PersonalInformation : ComponentActivity() {



    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DatabaseTestingWithHiltTheme {

                val navController = rememberNavController()
//                Scaffold(
//                topBar = {
//                    CenterAlignedTopAppBar(
//                        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
//                            containerColor = Color.Transparent
//                        ),
//                        title = {
//                            Text(
//                                text = "Personal Details",
//                                fontSize = 35.sp,
//                                color = Color.White,
//                                fontFamily = OutFitFontFamily,
//                                fontWeight = FontWeight.Bold
//                            )
//                        },
//                        navigationIcon = {
//                            IconButton(
//                                onClick = {}
//                            ) {
//                                Icon(
//                                    painter = painterResource(R.drawable.baseline_arrow_back_24),
//                                    contentDescription = null
//                                )
//                            }
//                        }
//                    )
//                }
//                ) { innerPadding ->
//
//                   // val navController = rememberNavController()
//                   UserNutritionForm()
//                    //navigateToPersonalInformation(navController)
//                }

                personalInfoNavigation(navController)
            }
        }
    }
}

@Composable
fun UserNutritionForm(viewModel: UserViewModel = hiltViewModel(),
                      firebaseViewmodel: FirebaseViewmodel = hiltViewModel()
) {

    val context = LocalContext.current
    var name by remember { mutableStateOf("") }
    var age by remember { mutableStateOf("") }
    var gender by remember { mutableStateOf("") }
    var weight by remember { mutableStateOf("") }
    var height by remember { mutableStateOf("") }
    var activityLevel by remember { mutableStateOf("") }
    var exerciseFrequency by remember { mutableStateOf(0f) }
    var occupationType by remember { mutableStateOf("") }
    var goal by remember { mutableStateOf("") }

    val goals = listOf("Maintain weight", "Lose weight", "Gain weight")
    val genderOptions = listOf("Male", "Female", "Other")
    val activityLevels = listOf("Sedentary", "Lightly Active", "Moderately Active", "Very Active", "Super Active")
    val occupationTypes = listOf("Desk Job", "Standing Job", "Manual Labor", "Mixed")

    Box(
        modifier = Modifier
            .fillMaxSize()
           // .padding(top = 80.dp)
           // .verticalScroll(rememberScrollState()),
    ) {
        Image(
                modifier = Modifier,
                painter = painterResource(R.drawable.mainbackground),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
            Column(modifier = Modifier.padding(16.dp,top = 100.dp)) {


                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Enter full name") },
                    shape = RoundedCornerShape(18.dp),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    colors = TextFieldDefaults.colors(
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White,

                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,

                        focusedLabelColor = Color.White,
                        unfocusedLabelColor = Color.Gray,

                        focusedIndicatorColor = Color.White,
                        unfocusedIndicatorColor = Color.Gray,

                        cursorColor = Color.White
                    ),
                    modifier = Modifier.fillMaxWidth()

                )
                // Age Input
                OutlinedTextField(
                    value = age,
                    onValueChange = { age = it },
                    label = { Text("Age") },
                    shape = RoundedCornerShape(18.dp),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    colors = TextFieldDefaults.colors(
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White,

                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,

                        focusedLabelColor = Color.White,
                        unfocusedLabelColor = Color.Gray,

                        focusedIndicatorColor = Color.White,
                        unfocusedIndicatorColor = Color.Gray,

                        cursorColor = Color.White
                    ),
                    modifier = Modifier.fillMaxWidth()

                )

                // Gender Dropdown
                DropdownSelector("Gender", gender, genderOptions) { gender = it }

                // Weight Input
                OutlinedTextField(
                    value = weight,
                    onValueChange = { weight = it },
                    label = { Text("Weight (kg)") },
                    shape = RoundedCornerShape(18.dp),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    colors = TextFieldDefaults.colors(
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White,

                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,

                        focusedLabelColor = Color.White,
                        unfocusedLabelColor = Color.Gray,

                        focusedIndicatorColor = Color.White,
                        unfocusedIndicatorColor = Color.Gray,

                        cursorColor = Color.White
                    ),
                    modifier = Modifier.fillMaxWidth()

                )

                // Height Input
                OutlinedTextField(
                    value = height,
                    onValueChange = { height = it },
                    label = { Text("Height (cm)") },
                    shape = RoundedCornerShape(18.dp),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    colors = TextFieldDefaults.colors(
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White,

                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,

                        focusedLabelColor = Color.White,
                        unfocusedLabelColor = Color.Gray,

                        focusedIndicatorColor = Color.White,
                        unfocusedIndicatorColor = Color.Gray,

                        cursorColor = Color.White
                    ),
                    modifier = Modifier.fillMaxWidth()

                )

                // Activity Level Dropdown
                DropdownSelector("Activity Level", activityLevel, activityLevels) { activityLevel = it }

                Spacer(modifier = Modifier.height(8.dp))

                // Exercise Frequency Slider
                Text("Exercise Frequency: ${exerciseFrequency.toInt()} days/week")
                Slider(
                    value = exerciseFrequency,
                    onValueChange = { exerciseFrequency = it },
                    valueRange = 0f..7f,
                    steps = 6,
                    colors = SliderDefaults.colors(
                        activeTrackColor = Color.Transparent, // Use transparent as we're handling the active track manually
                        inactiveTrackColor = gray,
                        thumbColor = Color(0xFF9C27B0) // Purple
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .drawWithContent {
                            // Draw gradient for the active portion of the track
                            val trackHeight = 4.dp.toPx()
                            val activeWidth = size.width * (exerciseFrequency / 7f) // Based on the progress value
                            val gradientBrush = Brush.horizontalGradient(
                                colors = listOf(Color.Black, Color(0xFF9C27B0)) // Black to purple gradient
                            )

                            drawRect(
                                brush = gradientBrush,
                                topLeft = Offset(0f, (size.height - trackHeight) / 2),
                                size = Size(activeWidth, trackHeight)
                            )

                            // Call original drawing logic
                            drawContent()
                        }
                )



                // Occupation Type Dropdown
                DropdownSelector("Occupation Type", occupationType, occupationTypes) { occupationType = it }

                Spacer(modifier = Modifier.height(10.dp))

                DropdownSelector("Your goal", goal, goals) { goal = it }

                Spacer(modifier = Modifier.height(20.dp))

                // Submit Button
                Button(
                    onClick = {
                        val NutritionValue = RequiredNutritionValue(
                            age.toIntOrNull() ?: 0,
                            gender,
                            weight.toFloatOrNull() ?: 0f,
                            height.toFloatOrNull() ?: 0f,
                            activityLevel,
                            goal
                        )
                        val calorie = NutritionValue["Calories"] ?:0f
                        val protein = NutritionValue["Protein"] ?:0f
                        val carbs = NutritionValue["Carbs"] ?:0f
                        val fats = NutritionValue["Fats"] ?:0f
                        val userData = PersonalEntity(
                            name = name,
                            age = age.toIntOrNull() ?: 0,
                            gender = gender,
                            weight = weight.toFloatOrNull() ?: 0f,
                            height = height.toFloatOrNull() ?: 0f,
                            activityLevel = activityLevel,
                            goal = goal,
                            exerciseFrequency = exerciseFrequency.toInt(),
                            occupationType = occupationType,
                            RequiredCalorie = calorie,
                            RequiredProtein = protein,
                            RequiredCarbs = carbs,
                            RequiredFats = fats
                        )
                        firebaseViewmodel.saveUserData(userData)

                        val i = Intent(context, MainActivity::class.java)
                        context.startActivity(i)
                        (context as Activity).finish()
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White,
                        contentColor = Color.Black
                    )
                ) {
                    Text("Calculate Nutrition Intake")
                }
            }
        }
}


// Dropdown Selector Composable

@Composable
fun DropdownSelector(
    label: String,
    selectedOption: String,
    options: List<String>,
    onOptionSelected: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxWidth()) {
        OutlinedTextField(
            value = selectedOption,
            onValueChange = {},
            label = { Text(label) },
            shape = RoundedCornerShape(18.dp),
            readOnly = true,
            trailingIcon = {
                IconButton(onClick = { expanded = !expanded }) {
                    Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = "Dropdown")
                }
            },
            modifier = Modifier.fillMaxWidth()

        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.fillMaxWidth()
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    text = { Text(option) },
                    onClick = {
                        onOptionSelected(option)
                        expanded = false
                    }
                )
            }
        }
    }
}


fun RequiredNutritionValue(
    age: Int,
    gender: String,
    weight: Float,
    height: Float,
    activity_level: String,
    goal: String
): Map<String, Float> {

    val ActivityLevelMultiplier = when (activity_level) {
        "Sedentary" -> 1.2f
        "Lightly Active" -> 1.375f
        "Moderately Active" -> 1.55f
        "Very Active" -> 1.725f
        "Super Active" -> 1.9f
        else -> 1.0f
    }

    val BMR = if (gender == "Male") {
        10f * weight + 6.25f * height - 5 * age + 5
    } else {
        10f * weight + 6.25f * height - 5 * age - 161
    }

    val TDEE = BMR * ActivityLevelMultiplier

    val finalCalories = when (goal) {
        "Maintain weight" -> TDEE
        "Lose weight" -> TDEE - 500
        "Gain weight" -> TDEE + 300
        else -> TDEE
    }

    // for Required Protein
    val proteinPerKg = 1.8f
    val RequiredProteinGrams = weight * proteinPerKg

    // Protein Calories: 1g protein = 4 kcal
    val ProteinCalories = RequiredProteinGrams * 4

    val fatPercentage = 0.25f
    val FatCalories = finalCalories * fatPercentage

    // Fat Grams: 1g fat = 9 kcal
    val RequiredFatGrams = FatCalories / 9

    val CarbohydrateCalories = finalCalories - (ProteinCalories + FatCalories)

    val RequiredCarbsGrams = CarbohydrateCalories / 4

    return mapOf(
        "Calories" to finalCalories,
        "Fats" to RequiredFatGrams,
        "Protein" to RequiredProteinGrams,
        "Carbs" to RequiredCarbsGrams
    )
}

@Composable
fun personalInfoNavigation(navController: NavHostController,
                           firebaseViewmodel: FirebaseViewmodel= hiltViewModel()){

    NavHost(navController = navController,
        startDestination = "Name"
    ){
        composable(route = "Name",){
            NameScreen(navController,firebaseViewmodel)
        }
        composable(route = "HeightAndWeight"){
            HeightAndWeight(navController,firebaseViewmodel)
        }
        composable(route = "AgeAndGender"){
            AgeandGenderScreen(navController,firebaseViewmodel)
        }
        composable(route = "Goal"){
            GoalScreen(navController, firebaseViewmodel)
        }
        composable(route = "ExerciseFrequency"){
            ExerciseFrequencyScreen(navController,firebaseViewmodel)
        }
        composable(route = "Occupation"){
            OccupationTypeScreen(navController,firebaseViewmodel)
        }
        composable(route = "ActivityLevel"){
            ActivityLevelScreen(navController,firebaseViewmodel)
        }

    }
}