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
import com.example.databasetestingwithhilt.ui.theme.White
import com.example.databasetestingwithhilt.ui.theme.purple
import com.example.databasetestingwithhilt.viewmodel.FirebaseViewmodel


@Composable
fun GoalScreen(navController: NavController, firebaseViewmodel: FirebaseViewmodel) {

    var selectedGoal by remember { mutableStateOf("") }

    val goals = listOf(
        "Maintain weight", "Lose weight", "Gain weight"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(18.dp)
    ) {

        Text("Welcome",
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Bold,
            fontSize = 26.sp)

        Spacer(modifier = Modifier.height(34.dp))

        Text(
            "Let's start with your main goal.",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold
        )
        Text(
            "Choose one that’s most important to you right now.",
            style = MaterialTheme.typography.bodySmall,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(16.dp))

        goals.forEach { goal ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        selectedGoal = goal
                        firebaseViewmodel.goal = goal
                    }
                    .padding(12.dp)
                    .background(
                        if (goal == selectedGoal) Color.Gray.copy(alpha = 0.2f) else Color.Transparent,
                        shape = RoundedCornerShape(8.dp)
                    ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = goal == selectedGoal,
                    onClick = {
                        selectedGoal = goal
                        firebaseViewmodel.goal = goal
                    }
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(goal)
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = {
                firebaseViewmodel.goal=selectedGoal
                navController.navigate("ExerciseFrequency")
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = purple,
                contentColor = White
            ),
            enabled = selectedGoal.isNotEmpty(),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Next")
        }
    }
}
