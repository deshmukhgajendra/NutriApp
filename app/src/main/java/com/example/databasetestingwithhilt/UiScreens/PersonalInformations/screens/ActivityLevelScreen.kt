package com.example.databasetestingwithhilt.UiScreens.PersonalInformations.screens

import android.app.Activity
import android.content.Intent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.databasetestingwithhilt.UiScreens.MainActivity
import com.example.databasetestingwithhilt.UiScreens.PersonalInformations.RequiredNutritionValue
import com.example.databasetestingwithhilt.model.PersonalEntity
import com.example.databasetestingwithhilt.ui.theme.White
import com.example.databasetestingwithhilt.ui.theme.fire
import com.example.databasetestingwithhilt.ui.theme.purple
import com.example.databasetestingwithhilt.viewmodel.FirebaseViewmodel


@Composable
fun ActivityLevelScreen(navController: NavController,firebaseViewmodel: FirebaseViewmodel) {

    var selectedActivityLevel by remember { mutableStateOf("") }
    val context = LocalContext.current


    val levels = listOf(
        "Not Very Active",
        "Lightly Active",
        "Active",
        "Very Active"
    )

   Box(
       modifier = Modifier.fillMaxSize()
   ) {
       Canvas(modifier = Modifier.fillMaxSize()) {
           val width = size.width
           val height = size.height

           // Top-right olive glow
           drawCircle(
               brush = Brush.radialGradient(
                   colors = listOf(
                       Color(0xFF787506), // olive yellow
                       Color.Transparent
                   ),
                   center = Offset(width, 0f),
                   radius = width * 0.7f
               ),
               radius = width * 0.7f,
               center = Offset(width, 0f)
           )

           // Bottom-left olive glow
           drawCircle(
               brush = Brush.radialGradient(
                   colors = listOf(
                       Color(0xFF787506), // olive yellow
                       Color.Transparent
                   ),
                   center = Offset(0f, height),
                   radius = width * 0.7f
               ),
               radius = width * 0.7f,
               center = Offset(0f, height)
           )
       }

       Column(
           modifier = Modifier
               .fillMaxSize()
               .padding(18.dp)
       ) {
           Text("What is your baseline activity level?", style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
           Text("Not including workouts - we count that separately.", style = MaterialTheme.typography.bodySmall, color = Color.Gray)

           Spacer(modifier = Modifier.height(16.dp))

           levels.forEach { level ->
               Row(
                   modifier = Modifier
                       .fillMaxWidth()
                       .clickable { selectedActivityLevel = level }
                       .padding(12.dp)
                       .background(
                           if (selectedActivityLevel == level) Color.DarkGray.copy(alpha = 0.3f) else Color.Transparent,
                           shape = RoundedCornerShape(8.dp)
                       ),
                   verticalAlignment = Alignment.CenterVertically
               ) {
                   RadioButton(
                       selected = selectedActivityLevel == level,
                       onClick = {
                           selectedActivityLevel = level
                       }
                   )
                   Spacer(modifier = Modifier.width(8.dp))
                   Text(level)
               }
           }

           Spacer(modifier = Modifier.weight(1f))

           Button(
               onClick = {
                   firebaseViewmodel.activityLevel = selectedActivityLevel




                   val NutritionValue = RequiredNutritionValue(
                       firebaseViewmodel.age,
                       firebaseViewmodel.gender,
                       firebaseViewmodel.weight,
                       firebaseViewmodel.height,
                       firebaseViewmodel.activityLevel,
                       firebaseViewmodel.goal
                   )
                   val calorie = NutritionValue["Calories"] ?:0f
                   val protein = NutritionValue["Protein"] ?:0f
                   val carbs = NutritionValue["Carbs"] ?:0f
                   val fats = NutritionValue["Fats"] ?:0f
                   val userData = PersonalEntity(
                       name = firebaseViewmodel.name,
                       age = firebaseViewmodel.age,
                       gender = firebaseViewmodel.gender,
                       weight = firebaseViewmodel.weight,
                       height = firebaseViewmodel.height,
                       activityLevel = firebaseViewmodel.activityLevel,
                       goal = firebaseViewmodel.goal,
                       exerciseFrequency = firebaseViewmodel.exerciseFrequency.toInt(),
                       occupationType = firebaseViewmodel.occupationType,
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
}