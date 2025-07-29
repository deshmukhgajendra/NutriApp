package com.example.databasetestingwithhilt.UiScreens.PersonalInformations.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.databasetestingwithhilt.ui.theme.White
import com.example.databasetestingwithhilt.ui.theme.gray
import com.example.databasetestingwithhilt.ui.theme.purple
import com.example.databasetestingwithhilt.viewmodel.FirebaseViewmodel


@Composable
fun NameScreen(navController: NavController,viewmodel: FirebaseViewmodel){
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

        Text("First, what can we call you?",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
            fontSize = 23.sp)

        Spacer(modifier = Modifier.height(8.dp))

        Text("We'd like to get to know you.",
            style = MaterialTheme.typography.bodySmall,
            color = gray,
            fontSize = 17.sp)

        Spacer(modifier = Modifier.height(40.dp))

        Text("Preferred first name",
            style = MaterialTheme.typography.bodySmall,
            color = Color.Gray,
            fontSize = 17.sp)
        OutlinedTextField(
            value = viewmodel.name,
            onValueChange = {viewmodel.name = it},
            label = { Text("Preferred first name") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = { navController.navigate("HeightAndWeight") },
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