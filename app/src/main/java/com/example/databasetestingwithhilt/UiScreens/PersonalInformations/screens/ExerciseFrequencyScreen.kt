package com.example.databasetestingwithhilt.UiScreens.PersonalInformations.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.databasetestingwithhilt.UiScreens.PersonalInformations.RequiredNutritionValue
import com.example.databasetestingwithhilt.model.PersonalEntity
import com.example.databasetestingwithhilt.ui.theme.White
import com.example.databasetestingwithhilt.ui.theme.fire
import com.example.databasetestingwithhilt.ui.theme.purple
import com.example.databasetestingwithhilt.viewmodel.FirebaseViewmodel


@Composable
fun ExerciseFrequencyScreen(navController: NavController,firebaseViewmodel: FirebaseViewmodel) {

    var selectedFrequency by remember { mutableStateOf("") }
    val workoutFrequency = listOf("1"," 2","3", "4","5", "6", "7" )


    Column(modifier = Modifier
        .fillMaxSize()
        .padding(18.dp)
    ){

        Text("Exercise",
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Bold,
            fontSize = 26.sp)

        Spacer(modifier = Modifier.height(34.dp))

        Text(
            "Your exercise frequency.",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold
        )
        Text("How frequently do you workout in a week ?",
            style = MaterialTheme.typography.bodySmall,
            color = Color.Gray, fontWeight = FontWeight.Bold)

        workoutFrequency.forEach{frequency ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        selectedFrequency = frequency
                        //firebaseViewmodel.goal = goal
                    }
                    .padding(8.dp)
                    .background(
                        if (frequency == selectedFrequency) Color.Gray.copy(alpha = 0.2f) else Color.Transparent,
                        shape = RoundedCornerShape(8.dp)
                    ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = frequency == selectedFrequency,
                    onClick = {
                        selectedFrequency = frequency
                      //  firebaseViewmodel.goal = goal
                    }
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(frequency)
            }
        }
        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = {
                val frequencyvalue = selectedFrequency.toInt()
                firebaseViewmodel.exerciseFrequency = frequencyvalue
                navController.navigate("Occupation") },
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