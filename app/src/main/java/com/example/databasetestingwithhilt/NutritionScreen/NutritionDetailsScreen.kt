package com.example.databasetestingwithhilt.NutritionScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.databasetestingwithhilt.UserViewModel
import dagger.hilt.android.lifecycle.HiltViewModel

@Composable
fun NutritionDetailsScreen(viewModel : UserViewModel = hiltViewModel()){

    val transfat by viewModel.liveTransfatCount.collectAsState()
    val vitaminA by viewModel.liveVitaminACount.collectAsState()
    val vitaminB6 by viewModel.liveVitaminB6Count.collectAsState()
    val vitaminB12 by viewModel.liveVitaminB12Count.collectAsState()
    val vitaminC by viewModel.liveVitaminB12Count.collectAsState()
    val vitaminD by viewModel.liveVitaminDCount.collectAsState()
    val vitaminE by viewModel.liveVitaminECount.collectAsState()
    val vitaminK by viewModel.liveVitaminKCount.collectAsState()
    val copper by viewModel.liveCopperCount.collectAsState()
    val zinc by viewModel.liveZincCount.collectAsState()
    val sodium by viewModel.liveSodiumCount.collectAsState()
    val potassium by viewModel.livePotassiumCount.collectAsState()
    val iron by viewModel.liveIronCount.collectAsState()
    val calcium by viewModel.liveCalorieCount.collectAsState()
    val fibar by viewModel.liveFibarCount.collectAsState()
    val suger by viewModel.liveSugerCount.collectAsState()
    val water by viewModel.liveWaterCount.collectAsState()
    val glucose by viewModel.liveGlucoseCount.collectAsState()
    val folicAcid by viewModel.liveGlucoseCount.collectAsState()
    val niacin by viewModel.liveNiacinCount.collectAsState()
    val retinol by viewModel.liveNiacinCount.collectAsState()
    val magnesium by viewModel.liveMagnesiumCount.collectAsState()
    val folate by viewModel.liveFolateCount.collectAsState()
    val cholesterol by viewModel.liveCholesteralCount.collectAsState()
    val monosaturatedFat by viewModel.liveMonosaturatedFatCount.collectAsState()
    val polusaturatedFat by viewModel.livePolysaturatedFatCount.collectAsState()



    LaunchedEffect(Unit) {
        viewModel.getTransFatCount()
        viewModel.getVitaminACount()
        viewModel.getVitaminB6()
        viewModel.getVitaminB12()
        viewModel.getVitaminCCount()
        viewModel.getVitaminD()
        viewModel.getVitaminE()
        viewModel.getVitaminK()
        viewModel.getCopper()
        viewModel.getZinc()
        viewModel.getSodium()
        viewModel.getPotassium()
        viewModel.getIron()
        viewModel.getCalcium()
        viewModel.getFibar()
        viewModel.getSuger()
        viewModel.getWater()
        viewModel.getGlucose()
        viewModel.getFolicAcid()
        viewModel.getNiacin()
        viewModel.getRetinol()
        viewModel.getMagnesium()
        viewModel.getFolate()
        viewModel.getCholesterol()
        viewModel.getMonosaturatedFatCount()
        viewModel.getPolysaturatedFatCount()
    }
    // create progressbars
    NutritionProgress("Protein",5.0,10.0)

}

@Composable
fun NutritionProgress(
    NutrientName: String,
    Progress: Double,
    Max: Double
) {

    val normalizedProgress = (Progress / Max).toFloat().coerceIn(0f, 1f)

    Card (modifier = Modifier.fillMaxWidth()
        .padding(4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = NutrientName,
                    color = Color.White,
                    fontSize = 14.sp,
                    textAlign = TextAlign.Start,
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = "${Progress.toInt()} / ${Max.toInt()}",
                    color = Color.White,
                    fontSize = 14.sp,
                    textAlign = TextAlign.End,
                    modifier = Modifier.weight(1f)
                )
            }

            HorizontalProgressBar(
                progress = normalizedProgress,
                backgroundColor = Color.Gray.copy(alpha = 0.3f),
                progressColor = Color.Green,
                height = 12.dp
            )
        }
    }
}

@Composable
fun HorizontalProgressBar(
    progress: Float,
    modifier: Modifier = Modifier,
    backgroundColor: Color = Color.LightGray,
    progressColor: Color = Color.Blue,
    height: Dp = 8.dp
){
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(height)
            .background(backgroundColor, shape = RoundedCornerShape(height / 2)),
        contentAlignment = Alignment.CenterStart
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(progress)
                .fillMaxHeight()
                .background(progressColor, shape = RoundedCornerShape(height / 2))
        )
    }
}