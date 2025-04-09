package com.example.databasetestingwithhilt.SearchScreen

import androidx.compose.runtime.Composable

import android.annotation.SuppressLint
import android.util.Log
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
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.databasetestingwithhilt.NutritionScreen.NutrientRequest
import com.example.databasetestingwithhilt.R
import com.example.databasetestingwithhilt.UserViewModel


@OptIn(ExperimentalMaterial3Api::class)
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
            .padding(16.dp)
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
            placeholder = { Text(text = "Search for food items...") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.search),
                    contentDescription = "Search Icon",
                    tint = MaterialTheme.colorScheme.primary
                )
            },
//            colors = TextFieldDefaults.outlinedTextFieldColors(
//                containerColor = MaterialTheme.colorScheme.surface,
//                focusedBorderColor = MaterialTheme.colorScheme.primary,
//                unfocusedBorderColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
//                cursorColor = MaterialTheme.colorScheme.primary
//            ),
            shape = MaterialTheme.shapes.medium,
            singleLine = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(
            contentPadding = PaddingValues(vertical = 8.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            items(result) { food ->
                Column(modifier = Modifier.padding(vertical = 8.dp)) {

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
                            ) {
                                Text(
                                    text = "Food: ${food.food_name}",
                                    style = MaterialTheme.typography.bodyMedium
                                )
                                Text(
                                    text = "Serving Quantity: ${food.serving_qty}",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = Color.Gray
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
                                  //  Log.d("TAG", "SearchScreen: $error ")
                                },
                                shape = RoundedCornerShape(100),
                                modifier = Modifier
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

