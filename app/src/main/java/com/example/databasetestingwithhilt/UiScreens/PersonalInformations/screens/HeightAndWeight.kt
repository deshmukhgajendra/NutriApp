package com.example.databasetestingwithhilt.UiScreens.PersonalInformations.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.databasetestingwithhilt.ui.theme.White
import com.example.databasetestingwithhilt.ui.theme.purple
import com.example.databasetestingwithhilt.viewmodel.FirebaseViewmodel

@Composable
fun HeightAndWeight(
    navController: NavController,
    firebaseViewmodel: FirebaseViewmodel
) {
    var heightUnit by remember { mutableStateOf("ft") }

    var heightFeet by remember { mutableStateOf("") }
    var heightInches by remember { mutableStateOf("") }
    var heightCm by remember { mutableStateOf("") }

    var weight by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(18.dp)
    ) {
        Text(
            "You",
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Bold,
            fontSize = 26.sp
        )

        Spacer(modifier = Modifier.height(34.dp))

        Text(
            "Just a few more questions",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text("How tall are you?", style = MaterialTheme.typography.bodyMedium)

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            if (heightUnit == "ft") {
                OutlinedTextField(
                    value = heightFeet,
                    onValueChange = { heightFeet = it },
                    label = { Text("Feet") },
                    modifier = Modifier.weight(1f),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
                Spacer(modifier = Modifier.width(8.dp))
                OutlinedTextField(
                    value = heightInches,
                    onValueChange = { heightInches = it },
                    label = { Text("Inches") },
                    modifier = Modifier.weight(1f),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
            } else {
                OutlinedTextField(
                    value = heightCm,
                    onValueChange = { heightCm = it },
                    label = { Text("Centimeters") },
                    modifier = Modifier.weight(1f),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
            }

            Spacer(modifier = Modifier.width(8.dp))

            Button(
                onClick = {
                    heightUnit = if (heightUnit == "ft") "cm" else "ft"
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = purple,
                    contentColor = White
                ),
                modifier = Modifier
                    .height(56.dp) // to match TextField height
            ) {
                Text(heightUnit.uppercase())
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text("How much do you weigh?", style = MaterialTheme.typography.bodyMedium)

        OutlinedTextField(
            value = weight,
            onValueChange = { weight = it },
            label = { Text("kg") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        Text(
            "It's OK to estimate, you can update later.",
            style = MaterialTheme.typography.bodySmall,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = {
                val finalHeightCm = if (heightUnit == "ft") {
                    val feet = heightFeet.toFloatOrNull() ?: 0f
                    val inch = heightInches.toFloatOrNull() ?: 0f
                    (feet * 30.48f) + (inch * 2.54f)
                } else {
                    heightCm.toFloatOrNull() ?: 0f
                }

                firebaseViewmodel.height = finalHeightCm
                firebaseViewmodel.weight = weight.toFloatOrNull() ?: 0f

                navController.navigate("Goal")
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = purple,
                contentColor = White
            ),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Next")
        }
    }
}
