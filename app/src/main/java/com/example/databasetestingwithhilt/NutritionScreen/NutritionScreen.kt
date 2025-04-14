package com.example.databasetestingwithhilt.NutritionScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.databasetestingwithhilt.R
import com.example.databasetestingwithhilt.UserViewModel
import com.example.databasetestingwithhilt.ui.theme.DarkBlue
import com.example.databasetestingwithhilt.ui.theme.darkGreen
import com.example.databasetestingwithhilt.ui.theme.gray
import com.example.databasetestingwithhilt.ui.theme.lightBlue
import com.example.databasetestingwithhilt.ui.theme.sea

@Composable
fun NutritionScreen(navController: NavController,viewModel: UserViewModel = hiltViewModel()){

    val foods by viewModel.foods.collectAsState()
    val error by viewModel.error.collectAsState()
    val scrollState = rememberScrollState()
    val requiredCalories by viewModel.requiredcaloriecount.collectAsState()
    val requiredProtein by viewModel.requiredproteincount.collectAsState()
    val requiredCarbs by viewModel.requiredcarbscount.collectAsState()
    val requiredFats by viewModel.requiredfatscount.collectAsState()
    val liveClorieCount by viewModel.liveCalorieCount.collectAsState()
    val liveProteinCount by viewModel.liveProteinCount.collectAsState()
    val liveCarbsCount by viewModel.liveCarbsCount.collectAsState()
    val liveFatsCount by viewModel.liveFatsCount.collectAsState()


    Column (modifier = Modifier
        .fillMaxSize()
        .verticalScroll(scrollState)
    ){

        CircularProgressBarCards(navController,liveClorieCount.toFloat(),requiredCalories.toFloat(),requiredCalories)
        MacrosCard(liveProteinCount,liveFatsCount,liveCarbsCount,requiredCarbs.toFloat(),requiredProtein.toFloat(),requiredFats.toFloat())
        GetFullInsights(navController)
        HabitCard()
        StepAndExerciseCard()
        DiscoverSection()
    }

    LaunchedEffect(Unit) {
        viewModel.getRequiredCalories()
        viewModel.getRequiredProteins()
        viewModel.getRequiredCarbs()
        viewModel.getRequiredFats()
        viewModel.getLiveCalorieCount()
        viewModel.getLiveFatsCount()
        viewModel.getLiveCarbsCount()
        viewModel.getLiveProteinCount()

//        Log.d("xyz", "RequiredProtein: $requiredProtein")
//        Log.d("xyz", "RequiredFats: $requiredFats")
//        Log.d("xyz", "RequiredCarbs: $requiredCarbs")
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CircularProgressBarCards(navController: NavController,Progress: Float, max: Float, requiredCalories: Int) {

    val progressFraction = if (max > 0) (Progress / max).coerceIn(0f, 1f) else 0f
    val remainingCalories = (max - Progress).coerceAtLeast(0f).toInt()

    Card(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .shadow(
                elevation = 6.dp,
                shape = RoundedCornerShape(16.dp)
            ),
        elevation = CardDefaults.cardElevation(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.Black)
    ) {
        Box(
            modifier = Modifier
                .padding(16.dp)
                .height(250.dp)
                .fillMaxWidth()
        ) {
            IconButton(
                onClick = { navController.navigate("FoodLog")},
                colors = IconButtonDefaults.iconButtonColors(containerColor = Color.Transparent),
                modifier = Modifier.align(Alignment.TopEnd)
            ) {
                Icon(
                    imageVector = Icons.Filled.List,
                    contentDescription = null,
                    tint = Color(0xFF007AFF)
                )
            }
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Row {
                    Column(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = stringResource(R.string.calories),
                            fontSize = 24.sp,
                            fontStyle = FontStyle.Normal,
                            color = colorResource(R.color.white),
                            textAlign = TextAlign.Start
                        )
                        Text(
                            text = "Daily Goal: ${requiredCalories} kcal",
                            fontSize = 16.sp,
                            color = Color.Gray,
                            textAlign = TextAlign.Start
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    // Progress Bar
                    Box(contentAlignment = Alignment.Center) {
                        CircularProgressIndicator(
                            progress = progressFraction,
                            color = darkGreen,
                            strokeWidth = 8.dp,
                            strokeCap = StrokeCap.Round,
                            trackColor = gray,
                            modifier = Modifier.size(130.dp)
                        )
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(text = "$remainingCalories", color = Color.White)
                            Text(text = "Remaining", color = Color.White)
                        }
                    }

                    // Icon Buttons (unchanged)
                    Column {
                        Row {
                            Button(
                                onClick = { /* TODO: Action 1 */ },
                                modifier = Modifier.padding(3.dp),
                                colors = ButtonDefaults.buttonColors(contentColor = Color.White)
                            ) {
                                Icon(
                                    painter = painterResource(R.drawable.flag),
                                    contentDescription = null,
                                    tint = Color.White,
                                )
                                Spacer(modifier = Modifier.padding(4.dp))
                                Column {
                                    Text(text = "Base Goal")
                                    Text(text = "$requiredCalories")
                                }
                            }
                        }
                        Row {
                            Button(
                                onClick = { /* TODO: Action 2 */ },
                                modifier = Modifier.padding(3.dp),
                                colors = ButtonDefaults.buttonColors(contentColor = Color.White)
                            ) {
                                Icon(
                                    painter = painterResource(R.drawable.dining),
                                    contentDescription = null,
                                    tint = colorResource(R.color.teal_200)
                                )
                                Spacer(modifier = Modifier.padding(8.dp))
                                Column {
                                    Text(text = "Food")
                                    Text(text = "${Progress.toInt()}")
                                }
                            }
                        }
                        Row {
                            Button(
                                onClick = { /* TODO: Action 3 */ },
                                modifier = Modifier.padding(3.dp),
                                colors = ButtonDefaults.buttonColors(contentColor = Color.White)
                            ) {
                                Icon(
                                    painter = painterResource(R.drawable.fire),
                                    contentDescription = null,
                                    tint = colorResource(R.color.yellow),
                                )
                                Spacer(modifier = Modifier.padding(8.dp))
                                Column {
                                    Text(text = "Exercise")
                                    Text(text = "2585")
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun MacrosCard(liveProtein: Float, liveCarbs: Float, liveFats: Float,
               requiredProtein: Float, requiredFats: Float, requiredCarbs: Float) {
//    Log.d("xyz", "Live Fats: $liveFats, Required Fats: $requiredFats")
//    Log.d("xyz", "Live Protein: $liveProtein, Required Protein: $requiredProtein")
//    Log.d("xyz", "Live Carbs: $liveCarbs, Required Carbs: $requiredCarbs")

    Card(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .shadow(
                elevation = 6.dp,
                shape = RoundedCornerShape(16.dp)
            ),
        elevation = CardDefaults.cardElevation(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.Black)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            // Title
            Text(
                text = "Macros",
                fontSize = 24.sp,
                color = colorResource(R.color.white),
                textAlign = TextAlign.Start
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Row for Progress Indicators
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                MacroProgress("Protein", liveProtein,requiredProtein, sea)
                MacroProgress("Carbs", liveCarbs,requiredCarbs, Color.Blue)
                MacroProgress("Fats", liveFats,requiredFats, Color.Yellow)
            }
        }
    }
}

@Composable
fun MacroProgress(label: String, Progress: Float, max: Float, color: Color) {
    val progressFraction = if (max > 0) (Progress / max).coerceIn(0f, 1f) else 0f

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(contentAlignment = Alignment.Center) {
            CircularProgressIndicator(
                progress = progressFraction,
                color = color,
                strokeWidth = 8.dp,
                strokeCap = StrokeCap.Round,
                trackColor = gray,
                modifier = Modifier.size(80.dp)
            )
            Text(
                text = "${(progressFraction * 100).toInt()}%",
                color = Color.White,
                fontSize = 14.sp,
                textAlign = TextAlign.Center
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = label,
            fontSize = 15.sp,
            color = Color.White,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun GetFullInsights(navController: NavController) {
    Card(
        onClick = {navController.navigate("NutritionDetails")},
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .shadow(
                elevation = 6.dp,
                shape = RoundedCornerShape(16.dp)
            ),
        elevation = CardDefaults.cardElevation(8.dp),
        colors = CardDefaults.cardColors(Color.Black)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Black.copy(alpha = 0.5f)) // Subtle overlay for readability
        ) {
            // Background image
            Image(
                painter = painterResource(R.drawable.button_background),
                contentDescription = null,
                modifier = Modifier
                    .matchParentSize()
                    .clip(RoundedCornerShape(16.dp)), // Rounded corners for the image
                contentScale = ContentScale.Crop
            )

            // Text content
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = "Get Full Insights",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    textAlign = TextAlign.Start
                )
                Spacer(modifier = Modifier.height(8.dp)) // Spacing between text elements
                Text(
                    text = "Get Full insights of your daily eating habits. Eat healthy Stay Fit",
                    fontSize = 14.sp,
                    color = Color.White.copy(alpha = 0.8f),
                    textAlign = TextAlign.Start
                )
            }
        }
    }
}


@Composable
fun HabitCard(){
    Card(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .shadow(
                elevation = 6.dp,
                shape = RoundedCornerShape(16.dp)
            ),
        elevation = CardDefaults.cardElevation(8.dp),
        colors = CardDefaults.cardColors(Color.Black)
    ) {
        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            // Background Image
            Image(
                painter = painterResource(id = R.drawable.background),
                contentDescription = null,
                modifier = Modifier
                    .matchParentSize(),
                contentScale = ContentScale.Crop
            )

            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .background(Color.Black.copy(alpha = 0.4f))
            ) {
                Text(
                    text = "Choose your next habit",
                    fontSize = 20.sp,
                    fontStyle = FontStyle.Normal,
                    textAlign = TextAlign.Start,
                    color = Color.White
                )
                Text(
                    text = "Big Goal starts with small habits",
                    fontSize = 13.sp,
                    color = Color.LightGray
                )

                Spacer(modifier = Modifier.height(8.dp))

                Button(
                    onClick = {},
                    shape = RoundedCornerShape(20.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = lightBlue,
                        contentColor = DarkBlue
                    )
                ) {
                    Text(text = "Start a habit")
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StepAndExerciseCard() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        // Steps Card
        Card(
            modifier = Modifier
                .weight(1f)
                .height(120.dp),
            colors = CardDefaults.cardColors(containerColor = Color.Black),
            shape = RoundedCornerShape(12.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Steps",
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.White
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Icon(
                        painter = painterResource(R.drawable.shoe_prints_svgrepo_com),
                        contentDescription = null,
                        tint = Color(0xFFFF4D4D), // Red color for icon
                        modifier = Modifier.size(24.dp)
                    )
                    Text(
                        text = "Connect to track steps.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Gray,
                        modifier = Modifier.padding(10.dp)
                    )
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowRight,
                        contentDescription = null,
                        tint = Color.Gray,
                        modifier = Modifier.size(24.dp).padding(6.dp)
                    )
                }
            }
        }

        // Exercise Card
        Card(
            modifier = Modifier
                .weight(1f)
                .height(120.dp),
            colors = CardDefaults.cardColors(containerColor = Color.Black),
            shape = RoundedCornerShape(12.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Exercise",
                        style = MaterialTheme.typography.titleMedium,
                        color = Color.White
                    )
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = null,
                        tint = Color.Gray,
                        modifier = Modifier.size(24.dp)
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(R.drawable.fire),
                        contentDescription = null,
                        tint = Color(0xFFFFA500),
                        modifier = Modifier.size(24.dp)
                    )
                    Text(
                        text = "0 cal",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Gray
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(R.drawable.alarm),
                        contentDescription = null,
                        tint = Color(0xFFFFA500),
                        modifier = Modifier.size(24.dp)
                    )
                    Text(
                        text = "0:00 hr",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Gray
                    )
                }
            }
        }
    }
}

@Composable
fun DiscoverSection() {
    Column(
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .background(Color(0xFF1C1C1E))
            .padding(16.dp)
    ) {
        Text(
            text = "Discover",
            style = MaterialTheme.typography.titleLarge,
            color = Color.White,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp)
        ) {
            items(listOf(
                Pair("Sleep", "Eat right, sleep tight") to R.drawable.sleep,
                Pair("Recipes", "Cook, eat, log, repeat") to R.drawable.recipe_svgrepo_com,
                Pair("Workouts", "Sweating is self-care") to R.drawable.workout_svgrepo_com,
                Pair("Friends", "Your support squad") to R.drawable.friends_svgrepo_com
            )) { (text, iconRes) ->
                DiscoverCard(
                    title = text.first,
                    subtitle = text.second,
                    iconRes = iconRes
                )
            }
        }
    }
}

@Composable
fun DiscoverCard(title: String, subtitle: String, iconRes: Int) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF1C1C1E)),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                painter = painterResource(iconRes),
                contentDescription = null,
                tint = Color(0xFF007AFF), // Blue color
                modifier = Modifier.size(32.dp)
            )
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                color = Color.White
            )
            Text(
                text = subtitle,
                style = MaterialTheme.typography.bodySmall,
                color = Color.Gray,
                textAlign = TextAlign.Center
            )
        }
    }
}

