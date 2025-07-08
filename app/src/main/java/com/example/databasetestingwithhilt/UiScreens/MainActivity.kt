package com.example.databasetestingwithhilt.UiScreens

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.databasetestingwithhilt.UiScreens.Dashboard.DashBoardScreen
import com.example.databasetestingwithhilt.UiScreens.MenuScreen.MenuScreen
import com.example.databasetestingwithhilt.UiScreens.NutritionScreen.FoodLogDetails
import com.example.databasetestingwithhilt.UiScreens.NutritionScreen.NutritionDetailsScreen
import com.example.databasetestingwithhilt.UiScreens.NutritionScreen.NutritionScreen
import com.example.databasetestingwithhilt.R
import com.example.databasetestingwithhilt.UiScreens.SearchScreen.SearchScreen
import com.example.databasetestingwithhilt.Services.StepCounterService
import com.example.databasetestingwithhilt.UiScreens.SleepScreen.SleepScreen
import com.example.databasetestingwithhilt.ui.theme.DatabaseTestingWithHiltTheme
import com.example.databasetestingwithhilt.ui.theme.OutFitFontFamily
import com.example.databasetestingwithhilt.ui.theme.White
import com.example.databasetestingwithhilt.ui.theme.purple
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {


    @RequiresApi(Build.VERSION_CODES.S)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestNecessaryPermissions()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val intent = Intent(this ,StepCounterService::class.java)
            startForegroundService(intent)
        }else{
            val intent = Intent(this, StepCounterService::class.java)
            startService(intent)
        }

        enableEdgeToEdge()
        setContent {
            DatabaseTestingWithHiltTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    app()
                }
            }
        }
    }

    fun requestNecessaryPermissions(){
        val permissionToRequest = mutableListOf<String>()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q){
            if (ContextCompat.checkSelfPermission(this,Manifest.permission.ACTIVITY_RECOGNITION)
                != PackageManager.PERMISSION_GRANTED){
                permissionToRequest.add(Manifest.permission.ACTIVITY_RECOGNITION)
            }
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
            if (ContextCompat.checkSelfPermission(this,Manifest.permission.POST_NOTIFICATIONS)
                != PackageManager.PERMISSION_GRANTED){
                permissionToRequest.add(Manifest.permission.POST_NOTIFICATIONS)
            }
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE){
            if (ContextCompat.checkSelfPermission(this,Manifest.permission.FOREGROUND_SERVICE_HEALTH)
                != PackageManager.PERMISSION_GRANTED){
                permissionToRequest.add(Manifest.permission.FOREGROUND_SERVICE_HEALTH)
            }
        }

        if (permissionToRequest.isNotEmpty()){
            ActivityCompat.requestPermissions(
                this,
                permissionToRequest.toTypedArray(),
                100
            )
        }
    }
}



@RequiresApi(Build.VERSION_CODES.S)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun app() {

val context = LocalContext.current
    val navController = rememberNavController()
    val sheetState = rememberModalBottomSheetState()
    var showBottomSheet = remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(

                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color.Transparent
                ),
                title = {
                    Text(
                        text = "Restify",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        color = purple,
                        fontSize = 35.sp,
                        fontFamily = OutFitFontFamily,
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = {
                        val i = Intent(context, NavigationDrawer::class.java)
                        context.startActivity(i)
                        //(context as Activity).finish()
                    },
                        modifier = Modifier
                            .size(50.dp)
                            .padding(8.dp)

                    ) {
                        Icon(
                            painterResource(R.drawable.accountcircle),
                            contentDescription = null,
                            modifier = Modifier.size(40.dp)  // Explicit icon size

                        )
                    }
                },
                actions = {
                    IconButton(onClick = {
                        val i = Intent(context, NavigationDrawer::class.java)
                        context.startActivity(i)
                    },
                        modifier = Modifier
                            .size(60.dp)
                            .padding(8.dp)

                    ) {
                        Icon(
                            painterResource(R.drawable.baseline_notifications_24),
                            contentDescription = null,
                            tint = Color.Yellow,
                            modifier = Modifier.size(40.dp)  // Explicit icon size

                        )
                    }
                }
            )

        },
        bottomBar = {

            bottomBar(navController,showBottomSheet)

        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Image(
                painter = painterResource(R.drawable.mainbackground),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )

            if (showBottomSheet.value){
                ModalBottomSheet(
                    onDismissRequest = {showBottomSheet.value = false },
                    sheetState = sheetState
                ) { // sheet content

                    GridWithButtons(navController,showBottomSheet)
                }
            }
                NavigateToScreen(navController,innerPadding)
        }
    }
}

@Composable
fun GridWithButtons(navController: NavController,showBottomSheet : MutableState<Boolean>) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2), // Two columns
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(4) { index ->
            Button(
                onClick = { /* Handle button click */
                    when (index) {
                        0 -> {navController.navigate("Search")
                        showBottomSheet.value = false}
                        1 -> {navController.navigate("Sleep")
                        showBottomSheet.value = false}
                    }
                },
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .size(100.dp)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Icon(
                        painter = when (index) {
                            0 -> painterResource(R.drawable.search)
                            1 -> painterResource(R.drawable.sleep)
                            2 -> painterResource(R.drawable.baseline_water_drop_24)
                            else -> painterResource(R.drawable.workout_svgrepo_com)
                        },
                        contentDescription = null,
                        modifier = Modifier.size(24.dp),
                        tint = White
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = when (index){
                            0 -> "Log Food"
                            1 -> "Log Sleep"
                            2 -> "Log Water"
                            else -> "Log Workout"
                        },
                        fontSize = 12.sp,
                        fontFamily = OutFitFontFamily,
                        fontWeight = FontWeight.Normal,
                        color = White
                    )
                }
            }
        }
    }
}



@Composable
fun bottomBar(navController: NavController, showBottomSheet : MutableState<Boolean>) {
    BottomAppBar(
        containerColor = MaterialTheme.colorScheme.primary,
        contentColor = MaterialTheme.colorScheme.tertiary
    ) {
        // Nutrition Button
        Box(
            modifier = Modifier
                .weight(1f)
                .padding(16.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(Color.Transparent)
                .padding(8.dp),
            contentAlignment = Alignment.Center
        ) {
            IconButton(onClick = { navController.navigate("Nutrition") }) {
                Icon(
                    painterResource(R.drawable.nutrition),
                    contentDescription = "Nutrition",
                    tint = MaterialTheme.colorScheme.secondary
                )
            }
        }

        // Sleep Button
        Box(
            modifier = Modifier
                .weight(1f)
                .padding(16.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(Color.Transparent)
                .padding(8.dp),
            contentAlignment = Alignment.Center
        ) {
            IconButton(onClick = {navController.navigate("Sleep")}) {
                Icon(
                    painterResource(R.drawable.sleep),
                    contentDescription = "Sleep",
                    tint = MaterialTheme.colorScheme.secondary
                )
            }
        }

        // FAB Button
        Box(
            modifier = Modifier
                .weight(1f)
                .padding(16.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(purple)
                .padding(8.dp),
            contentAlignment = Alignment.Center
        ) {
            IconButton(onClick = {
               // navController.navigate("Search")
                showBottomSheet.value = true
            }) {
                Icon(
                    Icons.Filled.Add,
                    contentDescription = "FAB",
                    tint = MaterialTheme.colorScheme.secondary
                )
            }
        }

        // Dashboard Button
        Box(
            modifier = Modifier
                .weight(1f)
                .padding(16.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(Color.Transparent)
                .padding(8.dp),
            contentAlignment = Alignment.Center
        ) {
            IconButton(onClick = { navController.navigate("Dashboard")}
            ) {
                Icon(
                    painterResource(R.drawable.dashboard),
                    contentDescription = "Dashboard",
                    tint = MaterialTheme.colorScheme.secondary
                )
            }
        }

        // Menu Button
        Box(
            modifier = Modifier
                .weight(1f)
                .padding(16.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(Color.Transparent)
                .padding(8.dp),
            contentAlignment = Alignment.Center
        ) {
            IconButton(onClick = { navController.navigate("Menu") }) {
                Icon(
                    Icons.Filled.Menu,
                    contentDescription = "Menu",
                    tint = MaterialTheme.colorScheme.secondary
                )
            }
        }
    }
}

@Composable
fun NavigateToScreen(navController: NavHostController, innerPadding: PaddingValues) {

    NavHost(navController = navController,
        startDestination = "Nutrition"
        ,modifier = Modifier.padding(innerPadding)) {

        composable(route = "Nutrition"){
            NutritionScreen(navController)
        }
        composable(route = "Sleep"){
            SleepScreen()
        }
        composable(route = "Dashboard"){
            DashBoardScreen()
        }
        composable(route = "Menu"){
            MenuScreen()
        }
        composable(route = "Search"){
            SearchScreen(navController)
        }
        composable(route ="FoodLog"){
            FoodLogDetails()
        }
        composable(route = "NutritionDetails"){
            NutritionDetailsScreen()
        }
    }
}




