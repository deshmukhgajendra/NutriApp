package com.example.databasetestingwithhilt.UiScreens.NutritionScreen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import com.example.databasetestingwithhilt.viewmodel.UserViewModel
import com.example.databasetestingwithhilt.ui.theme.Copper
import com.example.databasetestingwithhilt.ui.theme.Water
import com.example.databasetestingwithhilt.ui.theme.White
import com.example.databasetestingwithhilt.ui.theme.beige
import com.example.databasetestingwithhilt.ui.theme.blood
import com.example.databasetestingwithhilt.ui.theme.coral
import com.example.databasetestingwithhilt.ui.theme.darkGreen
import com.example.databasetestingwithhilt.ui.theme.fire
import com.example.databasetestingwithhilt.ui.theme.lightBlue
import com.example.databasetestingwithhilt.ui.theme.lightGreen
import com.example.databasetestingwithhilt.ui.theme.mint
import com.example.databasetestingwithhilt.ui.theme.orange
import com.example.databasetestingwithhilt.ui.theme.pink
import com.example.databasetestingwithhilt.ui.theme.purple
import com.example.databasetestingwithhilt.ui.theme.red
import com.example.databasetestingwithhilt.ui.theme.sea
import com.example.databasetestingwithhilt.ui.theme.silver
import com.example.databasetestingwithhilt.ui.theme.sunset
import com.example.databasetestingwithhilt.ui.theme.yellow

@Composable
fun NutritionDetailsScreen(viewModel : UserViewModel = hiltViewModel()){

    val protein by viewModel.liveProteinCount.collectAsState()
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
    val energy by viewModel.liveEnergyCount.collectAsState()
    val starch by viewModel.liveStarchCount.collectAsState()
    val Sucrose by viewModel.liveSucroseCount.collectAsState()
    val Fructose by viewModel.liveFructoseCount.collectAsState()
    val Lactose by viewModel.liveLactoseCount.collectAsState()
    val Alcohol by viewModel.liveAlcoholCount.collectAsState()
    val Caffeine by viewModel.liveCaffeineCount.collectAsState()
    val Manganese by viewModel.liveManganeseCount.collectAsState()
    val BetaCarotene by viewModel.liveBetaCarroteneCount.collectAsState()
    val Lycopene by viewModel.liveLycopeneCount.collectAsState()
    val SaturatedFat by viewModel.liveSaturatedFatCount.collectAsState()




    val scrollState = rememberScrollState()

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
        viewModel.getLiveProteinCount()
        viewModel.getEnergyCount()
        viewModel.getStarchCount()
        viewModel.getSucroseCount()
        viewModel.getFructoseCount()
        viewModel.getLactoseCount()
        viewModel.getAlcoholCount()
        viewModel.getCaffeineCount()
        viewModel.getManganeseCount()
        viewModel.getBetaCaroteneCount()
        viewModel.getLycopeneCount()
        viewModel.getSaturatedFatCount()

//        val record = NutrientRecord(
//            protein.toFloat(),transfat.toFloat(),vitaminA,vitaminB6,vitaminB12,vitaminC,vitaminD,vitaminE,vitaminK,copper,zinc,sodium,potassium,iron,calcium,fibar,suger,water,glucose,folicAcid,niacin,retinol,magnesium
//            ,folate,cholesterol,monosaturatedFat,polusaturatedFat,energy,starch,Sucrose,Fructose,Lactose,Alcohol,Caffeine,Manganese,BetaCarotene,Lycopene,SaturatedFat,monosaturatedFat,polusaturatedFat
//        )
//       Log.d("gajendra", "NutritionDetailsScreen: $energy ")
//        viewModel.SaveRecordsToFirebase(record,"1-5-2025")

    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(Color.Transparent)
    ) {
        // Header Card
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Name",
                    color = Color.White,
                    fontSize = 16.sp,
                    textAlign = TextAlign.Start,
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = "Current",
                    color = Color.White,
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = "Goal",
                    color = Color.White,
                    fontSize = 16.sp,
                    textAlign = TextAlign.End,
                    modifier = Modifier.weight(1f)
                )
            }
        }

        // Progress Bars
        Column (modifier= Modifier.
        verticalScroll(scrollState)
        ){
         //   NutritionProgress("Protein", protein.toDouble(), requiredProtein.toDouble(), Color.Blue)
         //   NutritionProgress("Fat", transfat.toDouble(), requiredFat.toDouble(), Color.Green)
            NutritionProgress("Vitamin A", vitaminA.toDouble(), 9000.0, red)
            NutritionProgress("Vitamin B6", vitaminB6.toDouble(), 1.7, yellow)
            NutritionProgress("Vitamin B12", vitaminB12.toDouble(), 2.8, mint)
            NutritionProgress("Vitamin C", vitaminC.toDouble(), 90.0, orange)
            NutritionProgress("Vitamin D", vitaminD.toDouble(), 600.0, coral)
            NutritionProgress("Vitamin E", vitaminE.toDouble(), 15.0,lightGreen)
            NutritionProgress("Vitamin K", vitaminK.toDouble(), 120.0, sunset)
            NutritionProgress("Copper", copper.toDouble(), 1000.0, Copper)
            NutritionProgress("Zinc", zinc.toDouble(), 12.0, silver)
            NutritionProgress("Sodium", sodium.toDouble(), 4000.0, Color.Blue)
            NutritionProgress("Potassium", potassium.toDouble(), 4700.0, blood)
            NutritionProgress("Iron", iron.toDouble(), 15.0,red)
            NutritionProgress("Calcium", calcium.toDouble(), 1200.0, sea)
            NutritionProgress("Fiber", fibar.toDouble(), 34.0, White)
            NutritionProgress("Suger", suger.toDouble(), 35.0, lightBlue)
            NutritionProgress("Water", water.toDouble(), 3400.0, Water)
            NutritionProgress("Glucose", glucose.toDouble(), 200.0, pink)
            NutritionProgress("Folic Acid", folicAcid.toDouble(), 500.0, fire)
            NutritionProgress("Niacin", niacin.toDouble(), 16.0, beige)
            NutritionProgress("Retinol", retinol.toDouble(), 900.0, purple)
            NutritionProgress("Magnesium", magnesium.toDouble(), 410.0, orange)
            NutritionProgress("Folate", folate.toDouble(), 500.0, Color.Blue)
            NutritionProgress("Cholesterol", cholesterol.toDouble(), 250.0, red)
            NutritionProgress("Monosaturated Fat", monosaturatedFat.toDouble(), 85.0, lightGreen)
            NutritionProgress("Polysaturated Fat", polusaturatedFat.toDouble(), 85.0, darkGreen)

            Log.d("gajendra", "NutritionDetailsScreen11: ${energy.toDouble()} ")
        }
    }
}
@Composable
fun NutritionProgress(
    NutrientName: String,
    Progress: Double,
    Max: Double,
    color: Color
) {
    val normalizedProgress = (Progress / Max).toFloat().coerceIn(0f, 1f)

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
            .background(Color.Transparent) // Ensure no background color
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
                    text = "${Progress.toInt()}",
                    color = Color.White,
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = "${Max.toInt()}",
                    color = Color.White,
                    fontSize = 14.sp,
                    textAlign = TextAlign.End,
                    modifier = Modifier.weight(1f)
                )
            }

            HorizontalProgressBar(
                progress = normalizedProgress,
                backgroundColor = Color.Gray.copy(alpha = 0.1f),
                progressColor = color,
                height = 6.dp
            )
        }
    }
}

@Composable
fun HorizontalProgressBar(
    progress: Float,
    modifier: Modifier = Modifier,
    backgroundColor: Color = Color.Gray.copy(alpha = 0.3f),
    progressColor: Color = Color.Blue,
    height: Dp = 6.dp
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(height)
            .background(backgroundColor)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(progress)
                .fillMaxHeight()
                .background(progressColor)
        )
    }
}
