package com.example.databasetestingwithhilt.DashboardScreen

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import java.time.LocalDate
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import co.yml.charts.axis.AxisData
import co.yml.charts.common.model.PlotType
import co.yml.charts.ui.barchart.BarChart
import co.yml.charts.ui.barchart.models.BarChartData
import co.yml.charts.ui.barchart.models.BarData
import co.yml.charts.ui.piechart.charts.PieChart
import co.yml.charts.ui.piechart.models.PieChartConfig
import co.yml.charts.ui.piechart.models.PieChartData
import com.example.databasetestingwithhilt.ui.theme.gray
import com.example.databasetestingwithhilt.ui.theme.lightGray
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import co.yml.charts.common.model.Point
import com.example.databasetestingwithhilt.Database.SleepData
import com.example.databasetestingwithhilt.UserViewModel
import com.example.databasetestingwithhilt.ui.theme.White
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DashBoardScreen(viewModel : UserViewModel= hiltViewModel()) {
    val selectedDate = remember { mutableStateOf(LocalDate.now()) }
    val protein by viewModel.proteinValue
    val fats by viewModel.fatsValue
    val carbs by viewModel.carbsValue
    val calories by viewModel.caloriesValue
    val sleepData by viewModel.sleepData
    val isLoading by viewModel.isLoading
    val error by viewModel.errorsleep
    val requiredCalories by viewModel.requiredCalories.collectAsState(initial = 0f)


    //val error by viewModel.errorfirebase
    val date = remember { "1-5-2025" }

    LaunchedEffect(date) {
        viewModel.fetchProtein(date)
        viewModel.fetchFats(date)
        viewModel.fetchCarbs(date)
        viewModel.fetchCalorie(date)
        viewModel.fetchRequiredCalories()
        viewModel.fetchSleepData(date)
    }
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        // Fixed header item (Date Selector)
        item {
            DateSelectorWithCalendar(
                selectedDate = selectedDate.value,
                onDateSelected = { selectedDate.value = it }
            )
        }

        // Nutrition Card
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp)
                    .shadow(
                        elevation = 8.dp,
                        shape = RoundedCornerShape(8.dp)
                    ),
                elevation = CardDefaults.cardElevation(8.dp),
                colors = CardDefaults.cardColors(Color.Black)
            ) {
                Column {
                    NutritionPieChart(protein,carbs,fats)

                    Divider(color = lightGray, modifier = Modifier.padding(top = 25.dp))

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(15.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Total Calories",
                            textAlign = TextAlign.Start,
                            modifier = Modifier.weight(1f),
                            color = White
                        )
                        Text(
                            text = "${calories?.toInt()}",
                            textAlign = TextAlign.End,
                            color = White
                        )
                    }
                    Divider(color = lightGray)

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(15.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Net Calories",
                            textAlign = TextAlign.Start,
                            modifier = Modifier.weight(1f),
                            color = White
                        )
                        Text(
                            text = "${calories?.toInt()}",
                            textAlign = TextAlign.End,
                            color = White
                        )
                    }
                    Divider(color = lightGray)

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(15.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Goal",
                            textAlign = TextAlign.Start,
                            modifier = Modifier.weight(1f),
                            color = White
                        )
                        Text(
                            text = "${requiredCalories.toInt()}",
                            textAlign = TextAlign.End,
                            color = White
                        )
                    }
                }
            }
        }

        // Sleep Card
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .shadow(
                        elevation = 8.dp,
                        shape = RoundedCornerShape(8.dp)
                    ),
                elevation = CardDefaults.cardElevation(8.dp),
                colors = CardDefaults.cardColors(Color.Black)
            ) {
               Column(
                   modifier = Modifier.fillMaxSize(),
                   horizontalAlignment = Alignment.CenterHorizontally
               ) {
                   Text( text = "Sleep Patterns",
                       textAlign = TextAlign.Center,
                       modifier = Modifier.padding(bottom = 16.dp,top = 16.dp),
                       style = MaterialTheme.typography.titleMedium,
                       fontSize = 20.sp,
                       color = White
                   )
                   SleepBarChart()
               }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DateSelectorWithCalendar(
    selectedDate: LocalDate,
    onDateSelected: (LocalDate) -> Unit
) {
    var isCalendarExpanded by remember { mutableStateOf(false) }
    val daysOfWeek = listOf("SUN", "MON", "TUE", "WED", "THU", "FRI", "SAT")
    val today = LocalDate.now()

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (!isCalendarExpanded) {
            // Horizontal Date Strip
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState())
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                for (i in 0..6) {
                    val date = today.plusDays(i.toLong())
                    val dayName = daysOfWeek[date.dayOfWeek.ordinal]
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .padding(horizontal = 8.dp)
                            .clickable { onDateSelected(date) }
                    ) {
                        Text(
                            text = dayName,
                          //  style = MaterialTheme.typography.body2,
                            color = if (date == selectedDate) Color.Yellow else Color.Gray
                        )
                        Text(
                            text = date.dayOfMonth.toString(),
                          //  style = MaterialTheme.typography.body1,
                            color = if (date == selectedDate) Color.Yellow else Color.White
                        )
                    }
                }
            }
        } else {
            // Calendar View
            CalendarView(
                currentMonth = today.monthValue,
                selectedDate = selectedDate,
                onDateSelected = onDateSelected
            )
        }

        // Arrow Toggle
        IconButton(
            onClick = { isCalendarExpanded = !isCalendarExpanded }
        ) {
            Icon(
                imageVector = if (isCalendarExpanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                contentDescription = "Toggle Calendar View",
                tint = Color.White
            )
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CalendarView(
    currentMonth: Int,
    selectedDate: LocalDate,
    onDateSelected: (LocalDate) -> Unit
) {
    val firstDayOfMonth = LocalDate.now().withDayOfMonth(1)
    val daysInMonth = firstDayOfMonth.lengthOfMonth()

    Column(
        modifier = Modifier.padding(8.dp)
    ) {
        Text(
            text = "${firstDayOfMonth.month.name} ${firstDayOfMonth.year}",
          //  style = MaterialTheme.typography.h6,
            color = Color.White,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        for (week in 0..4) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                for (day in 1..7) {
                    val date = firstDayOfMonth.plusDays((week * 7 + day - 1).toLong())
                    if (date.dayOfMonth > daysInMonth) break
                    Box(
                        modifier = Modifier
                            .padding(6.dp)
                            .size(40.dp)
                            .background(
                                if (date == selectedDate) Color.Yellow else Color.Gray,
                                shape = CircleShape
                            )
                            .clickable { onDateSelected(date) },
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = date.dayOfMonth.toString(),
                           // style = MaterialTheme.typography.body1,
                            color = Color.Black
                        )
                    }
                }
            }
        }
    }
}


@Composable
fun NutritionPieChart(protein:Float, carbs: Float, fats: Float) {

    // Sample nutrition data (replace with your actual data)
        val pieChartData = PieChartData(
            slices = listOf(
                PieChartData.Slice("Protein", protein, Color(0xFFFF5733)),
                PieChartData.Slice("Carbs", carbs, Color(0xFF33FF57)),
                PieChartData.Slice("Fats", fats, Color(0xFF3357FF))
            ),
            plotType = PlotType.Pie
        )


    val pieChartConfig = PieChartConfig(
        isAnimationEnable = true,
        showSliceLabels = true,
        activeSliceAlpha = 0.9f,
        inActiveSliceAlpha = 0.5f,
        animationDuration = 1500,
        labelVisible = true,
        labelColor = gray,
        strokeWidth = 120f,
        isEllipsizeEnabled = true
    )

    Column(
        modifier = Modifier.fillMaxWidth()
        ,horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Text(
            text = "Nutrition Breakdown",
            modifier = Modifier.padding(bottom = 16.dp,top = 16.dp),
            style = MaterialTheme.typography.titleMedium,
            fontSize = 20.sp,
            color = White
        )

        PieChart(
            modifier = Modifier.size(250.dp),
            pieChartData = pieChartData,
            pieChartConfig = pieChartConfig
        )

        // Legend
        Row(
            modifier = Modifier.padding(top = 16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            pieChartData.slices.forEach { slice ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(horizontal = 8.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(16.dp)
                            .background(slice.color)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(text = slice.label
                    , color = White)
                }
            }
        }
    }
}


@Composable
fun SleepBarChart() {
    // Sample sleep data (hours of sleep per day)
    val sleepData = listOf(
        BarData(label = "Mon", point = Point(0f, 8f), color = Color(0xFF4285F4)),
        BarData(label = "Tue", point = Point(1f, 2f), color = Color(0xFF4285F4)),
//        BarData(label = "Wed", point = Point(2f, 5.8f), color = Color(0xFF4285F4)),
//        BarData(label = "Thu", point = Point(3f, 6.0f), color = Color(0xFF4285F4)),
//        BarData(label = "Fri", point = Point(4f, 8.1f), color = Color(0xFF34A853)), // Weekend color
//        BarData(label = "Sat", point = Point(5f, 9.0f), color = Color(0xFF34A853)),
//        BarData(label = "Sun", point = Point(6f, 8.5f), color = Color(0xFF34A853))
    )

    // X-Axis Configuration
    val xAxisData = AxisData.Builder()
        .axisStepSize(30.dp)
        .steps(sleepData.size - 1)
        .startDrawPadding(48.dp)
        .bottomPadding(40.dp)
        .axisLabelAngle(20f)
        .labelData { index -> sleepData[index].label }
        .build()

    // Y-Axis Configuration
    val maxSleepValue = 24
    val yStepSize = 4
    val yAxisData = AxisData.Builder()
        .steps(yStepSize)
        .labelAndAxisLinePadding(20.dp)
        .axisOffset(20.dp)
        .labelData { value -> (value * (maxSleepValue / yStepSize)).toString() }
//        .topPadding(40.dp)
//        .startPadding(50.dp)
     //   .maxValue(maxSleepValue * 1.1f) // 10% padding above max value
        .build()

    // Bar Chart Configuration
    val barChartConfig = BarChartData(
        chartData = sleepData,
        xAxisData = xAxisData,
        yAxisData = yAxisData,
        barStyle = co.yml.charts.ui.barchart.models.BarStyle(
            paddingBetweenBars = 20.dp,
            barWidth = 25.dp
        ),
        showYAxis = true,
        showXAxis = true,
        horizontalExtraSpace = 10.dp
    )

    // Render the chart
    BarChart(
        modifier = Modifier
            .fillMaxWidth()
            .height(350.dp)
            .padding(0.dp),
        barChartData = barChartConfig
    )
}

fun timeStringToFloat(timeString: String): Float {
    return try {
        val format = SimpleDateFormat("h:mm a", Locale.getDefault())
        val date = format.parse(timeString) ?: return 0f
        val calendar = Calendar.getInstance().apply { time = date }
        calendar.get(Calendar.HOUR_OF_DAY) + calendar.get(Calendar.MINUTE) / 60f
    } catch (e: Exception) {
        0f // Return 0 if parsing fails
    }
}


