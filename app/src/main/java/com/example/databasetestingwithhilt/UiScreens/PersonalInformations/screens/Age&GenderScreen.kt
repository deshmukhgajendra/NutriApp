package com.example.databasetestingwithhilt.UiScreens.PersonalInformations.screens

import android.widget.ToggleButton
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.example.databasetestingwithhilt.ui.theme.gray
import com.example.databasetestingwithhilt.ui.theme.purple
import com.example.databasetestingwithhilt.viewmodel.FirebaseViewmodel

@Composable
fun AgeandGenderScreen(navController: NavController,firebaseViewmodel: FirebaseViewmodel){

    var selectedSex by remember { mutableStateOf("") }
    var ageText by remember { mutableStateOf("") }

    val sexOptions = listOf("Male", "Female")

    Box(
        modifier = Modifier.fillMaxSize()
    ){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(18.dp)
        ) {

            Text("Welcome",
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold,
                fontSize = 28.sp)

            Spacer(modifier = Modifier.height(34.dp))


            Text(
                text = "Tell us a little bit about yourself",
                style = MaterialTheme.typography.titleMedium,
                fontSize = 26.sp
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text(text = "Please select which sex we should use to calculate your calorie needs:"
                , style = MaterialTheme.typography.bodySmall,
                color = gray,
                fontSize = 17.sp)

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                sexOptions.forEach { sex ->
                    val isSelected = selectedSex == sex
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .border(
                                2.dp,
                                if (isSelected) Color.Blue else Color.Gray,
                                RoundedCornerShape(12.dp)
                            )
                            .background(
                                color = if (isSelected) Color(0xFF1A1A1A) else Color.DarkGray,
                                shape = RoundedCornerShape(12.dp)
                            )
                            .clickable { selectedSex = sex }
                            .padding(vertical = 16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = sex,
                            color = if (isSelected) Color.White else Color.LightGray
                        )
                    }
                }
            }


            Spacer(modifier = Modifier.height(24.dp))

            Text("How old are you?")
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = ageText,
                onValueChange = { ageText = it },
                placeholder = { Text("Enter your age") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = {
                    firebaseViewmodel.gender = selectedSex
                    val selectedAge = ageText.toInt()?:0
                    firebaseViewmodel.age = selectedAge
                    navController.navigate("Goal")
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = purple,
                    contentColor = White
                ),
                enabled = selectedSex.isNotEmpty() && ageText.isNotEmpty(),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Next")
            }
        }
    }

}