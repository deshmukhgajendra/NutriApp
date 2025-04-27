package com.example.databasetestingwithhilt.NutritionScreen

import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.databasetestingwithhilt.UserViewModel
import com.example.databasetestingwithhilt.ui.theme.DarkBlue
import com.example.databasetestingwithhilt.ui.theme.red
import com.example.databasetestingwithhilt.ui.theme.sea
import com.example.databasetestingwithhilt.ui.theme.yellow

@Composable
fun FoodLogDetails(viewModel: UserViewModel= hiltViewModel()){
    Column (
        modifier = Modifier
            .fillMaxSize()
            //.verticalScroll(rememberScrollState())
            .padding(top = 16.dp)
    ) {
        PieChart(
            data = mapOf(
                Pair("Breakfast", 150),
                Pair("lunch", 120),
                Pair("Dinner", 110),
                Pair("Snacks", 170)
            )
        )
        FoodCard(viewModel)
    }

}
@OptIn(ExperimentalLayoutApi::class)
@Composable
fun FoodCard(viewModel: UserViewModel) {

    val foodNamesState = viewModel.foodNames.collectAsState(initial = emptyList())

    LaunchedEffect(Unit) {
        viewModel.getAllFoodName()
    }

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
                .fillMaxSize()
                .padding(16.dp)
        ) {
            LazyColumn {
                item {
                    FlowRow(
                        modifier = Modifier.fillMaxWidth(),
                       // mainAxisSpacing = 8.dp, // Adjust space between chips horizontally
                        //crossAxisSpacing = 8.dp // Adjust space between rows of chips
                    ) {
                        foodNamesState.value.forEach { food ->
                            AssistChip(
                                onClick = {
                                    val foodname = food
                                    viewModel.deleteFoodRecord(foodname)
                                    viewModel.removeFood(food)
                                },
                                label = {
                                    Text(text = food, color = Color.White)
                                },
                                trailingIcon = {
                                    Icon(
                                        imageVector = Icons.Filled.Close,
                                        contentDescription = null,
                                        tint = Color.White
                                    )
                                },
                                colors = AssistChipDefaults.assistChipColors(
                                    containerColor = Color.DarkGray
                                )
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun PieChart(
    data: Map<String, Int>,
    radiusOuter: Dp = 100.dp, // Reduced size
    chartBarWidth: Dp = 20.dp, // Adjusted stroke width
    animDuration: Int = 1000,
) {
    val totalSum = data.values.sum()
    val floatValue = mutableListOf<Float>()

    data.values.forEachIndexed { index, values ->
        floatValue.add(index, 360 * values.toFloat() / totalSum.toFloat())
    }

    val colors = listOf(
        red,
        sea,
        DarkBlue,
        yellow
    )

    var animationPlayed by remember { mutableStateOf(false) }
    var lastValue = 0f

    val animateSize by animateFloatAsState(
        targetValue = if (animationPlayed) radiusOuter.value * 2f else 0f,
        animationSpec = tween(
            durationMillis = animDuration,
            easing = LinearOutSlowInEasing
        )
    )

    val animateRotation by animateFloatAsState(
        targetValue = if (animationPlayed) 90f * 11f else 0f,
        animationSpec = tween(
            durationMillis = animDuration,
            easing = LinearOutSlowInEasing
        )
    )

    LaunchedEffect(key1 = true) {
        animationPlayed = true
    }

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier.size(animateSize.dp),
            contentAlignment = Alignment.Center
        ) {
            Canvas(
                modifier = Modifier
                    .size(radiusOuter * 2f)
                    .rotate(animateRotation)
            ) {
                floatValue.forEachIndexed { index, value ->
                    drawArc(
                        color = colors[index],
                        lastValue,
                        value,
                        useCenter = false,
                        style = Stroke(chartBarWidth.toPx(), cap = StrokeCap.Butt)
                    )
                    lastValue += value
                }
            }
        }

        DetailsPieChart(
            data = data,
            colors = colors
        )
    }
}

@Composable
fun DetailsPieChart(
    data: Map<String, Int>,
    colors: List<Color>
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 100.dp), // Items adapt to available width
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp), // Space between rows
        horizontalArrangement = Arrangement.spacedBy(8.dp) // Space between columns
    ) {
        items(data.values.size) { index ->
            DetailsPieChartItem(
                data = Pair(data.keys.elementAt(index), data.values.elementAt(index)),
                color = colors[index % colors.size]
            )
        }
    }
}

@Composable
fun DetailsPieChartItem(
    data: Pair<String, Int>,
    height: Dp = 40.dp,
    color: Color
) {
    Surface(
        modifier = Modifier
            .size(120.dp) // Adjust size for the grid
            .padding(4.dp),
        color = Color.Transparent
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .background(color = color, shape = RoundedCornerShape(8.dp))
                .padding(8.dp)
        ) {
            Text(
                text = data.first,
                fontWeight = FontWeight.Medium,
                fontSize = 12.sp, // Compact font size
                color = Color.White
            )
            Text(
                text = "${data.second}",
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                color = Color.White
            )
        }
    }
}
