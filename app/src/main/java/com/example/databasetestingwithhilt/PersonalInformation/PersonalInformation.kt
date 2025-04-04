package com.example.databasetestingwithhilt.PersonalInformation

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
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
import com.example.databasetestingwithhilt.ui.theme.DatabaseTestingWithHiltTheme
import dagger.hilt.android.AndroidEntryPoint



@AndroidEntryPoint
class PersonalInformation : ComponentActivity() {

    val userViewModel : UserViewModel by viewModels()

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

    val context= LocalContext.current
    var age by remember { mutableStateOf("") }
    var gender by remember { mutableStateOf("") }
    var weight by remember { mutableStateOf("") }
    var height by remember { mutableStateOf("") }
    var activityLevel by remember { mutableStateOf("") }
    var exerciseFrequency by remember { mutableStateOf(0f) }
    var occupationType by remember { mutableStateOf("") }

    val genderOptions = listOf("Male", "Female", "Other")
    val activityLevels = listOf("Sedentary", "Lightly Active", "Moderately Active", "Very Active", "Super Active")
    val occupationTypes = listOf("Desk Job", "Standing Job", "Manual Labor", "Mixed")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        Spacer(modifier = Modifier.height(40.dp))

        Text(text= "Personal Details",
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

        // Submit Button
        Button(
            onClick = {
                val userData = PersonalEntity(
                    age = age.toIntOrNull() ?: 0,
                    gender = gender,
                    weight = weight.toFloatOrNull() ?: 0f,
                    height = height.toFloatOrNull() ?: 0f,
                    activityLevel = activityLevel,
                    exerciseFrequency = exerciseFrequency.toInt(),
                    occupationType = occupationType
                )
              viewModel.insertPersonalData(userData)

                val i = Intent(context,MainActivity::class.java)
                context.startActivity(i)
            },
            modifier = Modifier.fillMaxWidth()
            , colors = ButtonDefaults.buttonColors(
                containerColor = Color.White,
                contentColor = Color.Black
            )
        ) {
            Text("Calculate Nutrition Intake")
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
