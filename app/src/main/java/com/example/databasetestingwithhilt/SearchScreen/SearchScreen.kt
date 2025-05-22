package com.example.databasetestingwithhilt.SearchScreen

import androidx.compose.runtime.Composable

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.databasetestingwithhilt.NutritionScreen.NutrientRequest
import com.example.databasetestingwithhilt.R
import com.example.databasetestingwithhilt.UserViewModel
import com.example.databasetestingwithhilt.ui.theme.OutFitFontFamily
import com.example.databasetestingwithhilt.ui.theme.White


@SuppressLint("UnrememberedMutableState")
@Composable
fun SearchScreen(navController: NavController, viewModel: UserViewModel = hiltViewModel()) {
    val context = LocalContext.current
    val result by viewModel.searchResults.collectAsState() // Observing search results
    val foods by viewModel.foods.collectAsState() // Observing foods (moved here)
    val error by remember { viewModel.errorMessage }
    var query by remember { mutableStateOf("") } // State for search query

    Column(
        modifier = Modifier
            .padding(top = 16.dp, start = 10.dp,end = 10.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.Start
    ) {
        // Search Bar
        OutlinedTextField(
            value = query,
            onValueChange = {
                query = it
                viewModel.fetchSearchResult(it) // Trigger API call on query change
            },
            placeholder = {
                Text(
                    text = "Search for food items...",
                    fontFamily = OutFitFontFamily,
                    fontWeight = FontWeight.Normal
                ) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.search),
                    contentDescription = "Search Icon",
                    tint = White
                )
            },
            shape = MaterialTheme.shapes.medium,
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
            singleLine = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(
            contentPadding = PaddingValues(vertical = 8.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            items(result) { food ->
                Column(modifier = Modifier.padding(vertical = 2.dp)) {

                    Card(
                        onClick = {},
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(1.dp),
                        elevation = CardDefaults.cardElevation(4.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(6.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(
                                modifier = Modifier.weight(1f)
                                    .padding(4.dp)
                            ) {
                                Text(
                                    text = "Food: ${food.food_name}",
                                    fontSize = 16.sp,
                                    fontFamily = OutFitFontFamily,
                                    fontWeight = FontWeight.Bold
                                )
                                Text(
                                    text = "Serving Quantity: ${food.serving_qty}",
                                    color = Color.Gray,
                                    fontSize = 12.sp,
                                    fontFamily = OutFitFontFamily,
                                    fontWeight = FontWeight.Normal
                                )
                            }

                            Button(
                                onClick = {
                                    val request = NutrientRequest(query = food.food_name)
                                    viewModel.fetchNutrients(request)

                                    foods.firstOrNull()?.let { response ->
                                        viewModel.logFood(
                                            response.food_name,
                                            response.full_nutrients
                                        )
                                    }
                                    Toast.makeText(context.applicationContext,"Food Logged !",Toast.LENGTH_LONG).show()
                                },
                                shape = RoundedCornerShape(50.dp),
                                modifier = Modifier
                                    .weight(0.2f)
                                    .height(40.dp)
                                    .align(Alignment.CenterVertically)
                            ) {
                                Icon(
                                    Icons.Filled.Add,
                                    contentDescription = null
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

