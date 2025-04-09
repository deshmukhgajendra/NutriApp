package com.example.databasetestingwithhilt.PersonalInformation

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.databasetestingwithhilt.Database.PersonalEntity
import com.example.databasetestingwithhilt.MainActivity
import com.example.databasetestingwithhilt.UserViewModel
import com.example.databasetestingwithhilt.ui.theme.DarkBlue
import com.example.databasetestingwithhilt.ui.theme.DatabaseTestingWithHiltTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class PersonalInformation : ComponentActivity() {

    //val userViewModel : UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DatabaseTestingWithHiltTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                   UserNutritionForm()
                }
            }
        }
    }
}

@Composable
fun UserNutritionForm(viewModel: UserViewModel = hiltViewModel()) {

    val context = LocalContext.current
    var age by remember { mutableStateOf("") }
    var gender by remember { mutableStateOf("") }
    var weight by remember { mutableStateOf("") } // Changed to String
    var height by remember { mutableStateOf("") } // Changed to String
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
            .background(brush = Brush.verticalGradient(listOf(DarkBlue, Color.Black)))
            .verticalScroll(rememberScrollState()),
    ) {
        Box(modifier = Modifier.padding(16.dp)) {
            Column {
                Spacer(modifier = Modifier.height(40.dp))

                Text(
                    text = "Personal Details",
                    textAlign = TextAlign.Center,
                    fontSize = 35.sp,
                    fontStyle = FontStyle.Normal,
                    color = Color.White
                )

                Spacer(modifier = Modifier.height(40.dp))

                // Age Input
                OutlinedTextField(
                    value = age,
                    onValueChange = { age = it },
                    label = { Text("Age") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.fillMaxWidth()
                )

                // Gender Dropdown
                DropdownSelector("Gender", gender, genderOptions) { gender = it }

                // Weight Input
                OutlinedTextField(
                    value = weight,
                    onValueChange = { weight = it },
                    label = { Text("Weight (kg)") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.fillMaxWidth()
                )

                // Height Input
                OutlinedTextField(
                    value = height,
                    onValueChange = { height = it },
                    label = { Text("Height (cm)") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
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
                    modifier = Modifier.fillMaxWidth()
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
                            age = age.toIntOrNull() ?: 0,
                            gender = gender,
                            weight = weight.toFloatOrNull() ?: 0f,
                            height = height.toFloatOrNull() ?: 0f,
                            activityLevel = activityLevel,
                            exerciseFrequency = exerciseFrequency.toInt(),
                            occupationType = occupationType,
                            RequiredCalorie = calorie,
                            RequiredProtein = protein,
                            RequiredCarbs = carbs,
                            RequiredFats = fats
                        )
                        viewModel.insertPersonalData(userData)

                        val i = Intent(context, MainActivity::class.java)
                        context.startActivity(i)
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
