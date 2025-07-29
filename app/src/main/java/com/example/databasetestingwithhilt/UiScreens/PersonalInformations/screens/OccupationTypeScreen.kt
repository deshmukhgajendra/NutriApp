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
import androidx.navigation.NavController
import com.example.databasetestingwithhilt.ui.theme.White
import com.example.databasetestingwithhilt.ui.theme.purple
import com.example.databasetestingwithhilt.viewmodel.FirebaseViewmodel


@Composable
fun  OccupationTypeScreen(navController: NavController, firebaseViewmodel: FirebaseViewmodel) {

    var selectedOccupation by remember { mutableStateOf("") }

    val occupations = listOf("Desk Job", "Standing Job", "Manual Labor", "Mixed")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(18.dp)
    ) {
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

        occupations.forEach { occupation ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        selectedOccupation = occupation
                       // firebaseViewmodel.goal = occupation
                    }
                    .padding(12.dp)
                    .background(
                        if (occupation == selectedOccupation) Color.Gray.copy(alpha = 0.2f) else Color.Transparent,
                        shape = RoundedCornerShape(8.dp)
                    ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = occupation == selectedOccupation,
                    onClick = {
                        selectedOccupation = occupation
                      //  firebaseViewmodel.goal = occupation
                    }
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(occupation)
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = {
                firebaseViewmodel.occupationType = selectedOccupation
                navController.navigate("ActivityLevel")
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = purple,
                contentColor = White
            ),
            enabled = selectedOccupation.isNotEmpty(),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Next")
        }
    }
}