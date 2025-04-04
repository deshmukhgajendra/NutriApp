package com.example.databasetestingwithhilt.NutritionScreen

import android.util.Log
import android.widget.HorizontalScrollView
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.databasetestingwithhilt.R
import com.example.databasetestingwithhilt.UserViewModel
import com.example.databasetestingwithhilt.ui.theme.DarkBlue
import com.example.databasetestingwithhilt.ui.theme.darkGreen
import com.example.databasetestingwithhilt.ui.theme.gray
import com.example.databasetestingwithhilt.ui.theme.lightBlue
import com.example.databasetestingwithhilt.ui.theme.lightGreen
import com.example.databasetestingwithhilt.ui.theme.sea

@Composable
fun NutritionScreen(viewModel: UserViewModel = hiltViewModel()){

    val foods by viewModel.foods.collectAsState()
    val error by viewModel.error.collectAsState()
    val scrollState = rememberScrollState()
    val liveClorieCount by viewModel.liveCalorieCount.collectAsState()

    Column (modifier = Modifier
        .fillMaxSize()
        .verticalScroll(scrollState)
    ){
        CircularProgressBarCards(liveClorieCount.toFloat(),2000f)
        MacrosCard()
        HabitCard()
    }

    LaunchedEffect(Unit) {
       viewModel.getLiveCalorieCount()
    }

}

@Composable
fun CircularProgressBarCards(Progress : Float, max :Float){

    val progressFraction = (Progress /max).coerceIn(0f,1f)
    Card(
            modifier = Modifier
                .padding(16.dp) // Padding for spacing
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
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    // Title Texts
                    Column(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = stringResource(R.string.calories),
                            fontSize = 24.sp, // Larger font size for title
                            fontStyle = FontStyle.Normal,
                            color = colorResource(R.color.white),
                            textAlign = TextAlign.Start
                        )
                        Text(
                            text = "Daily Goal: 1500 kcal", // Subtitle
                            fontSize = 16.sp,
                            color = Color.Gray,
                            textAlign = TextAlign.Start
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Row for Progress Bar and Icons
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        // Progress Bar
                        Box (contentAlignment = Alignment.Center
                        ){
                            CircularProgressIndicator(
                                progress = progressFraction,
                                color = darkGreen,
                                strokeWidth = 8.dp,
                                strokeCap = StrokeCap.Round
                                ,trackColor = gray
                                ,modifier = Modifier.size(130.dp) // Adjusted size
                            )
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Text(text = "5456"
                                    , color = Color.White)
                                Text(text = "Remaining"
                                    , color = Color.White)
                            }
                        }

                        // Icon Buttons
                        Column{
                            Row {
                                Button(onClick = { /* TODO: Action 1 */ },
                                    modifier = Modifier.padding(3.dp),
                                    colors = ButtonDefaults.buttonColors(
                                        contentColor = Color.White)
                                ){

                                    Icon(
                                        painter = painterResource(R.drawable.flag),
                                        contentDescription = null,
                                        tint = Color.White,
                                    )
                                    Spacer(modifier = Modifier.padding(4.dp))

                                    Column {
                                        Text(text = "Base Goal")
                                        Text(text = "2585")
                                    }
                                }
                            }
                            Row {
                                Button(onClick = { /* TODO: Action 1 */ },
                                    modifier = Modifier.padding(3.dp),
                                    colors = ButtonDefaults.buttonColors(
                                        contentColor = Color.White
                                    ),

                                    ) {
                                    Icon(
                                        painter = painterResource(R.drawable.dining),
                                        contentDescription = null,
                                        tint = colorResource(R.color.teal_200)
                                    )
                                    Spacer(modifier = Modifier.padding(8.dp))

                                    Column {
                                        Text(text = "Food")
                                        Text(text = "2585")
                                    }
                                }
                            }
                            Row {
                                Button(onClick = { /* TODO: Action 1 */ },
                                    modifier = Modifier.padding(3.dp),
                                    colors = ButtonDefaults.buttonColors(
                                        contentColor = Color.White
                                    ),

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
fun MacrosCard() {
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
                MacroProgress("Protein", 0.7f, sea)
                MacroProgress("Carbs", 0.5f, Color.Blue)
                MacroProgress("Fats", 0.3f, Color.Yellow)
            }
        }
    }
}

@Composable
fun MacroProgress(label: String, progress: Float, color: Color) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(contentAlignment = Alignment.Center) {
            CircularProgressIndicator(
                progress = progress,
                color = color,
                strokeWidth = 8.dp,
                strokeCap = StrokeCap.Round,
                trackColor = gray,
                modifier = Modifier.size(80.dp)
            )
            Text(
                text = "${(progress * 100).toInt()}%", // Showing percentage
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
                painter = painterResource(id = R.drawable.background), // Replace with your image
                contentDescription = null,
                modifier = Modifier
                    .matchParentSize(), // Makes image fill the box
                contentScale = ContentScale.Crop // Crop to fit the card
            )

            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .background(Color.Black.copy(alpha = 0.4f)) // Dark overlay for readability
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