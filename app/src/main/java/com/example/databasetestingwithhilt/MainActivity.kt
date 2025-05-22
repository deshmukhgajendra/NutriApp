package com.example.databasetestingwithhilt

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
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
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.databasetestingwithhilt.DashboardScreen.DashBoardScreen
import com.example.databasetestingwithhilt.MenuScreen.MenuScreen
import com.example.databasetestingwithhilt.MenuScreen.NavigationDrawer
import com.example.databasetestingwithhilt.NutritionScreen.FoodLogDetails
import com.example.databasetestingwithhilt.NutritionScreen.NutritionDetailsScreen
import com.example.databasetestingwithhilt.NutritionScreen.NutritionScreen
import com.example.databasetestingwithhilt.SearchScreen.SearchScreen
import com.example.databasetestingwithhilt.SleepScreen.SleepScreen
import com.example.databasetestingwithhilt.ui.theme.DatabaseTestingWithHiltTheme
import com.example.databasetestingwithhilt.ui.theme.OutFitFontFamily
import com.example.databasetestingwithhilt.ui.theme.purple
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

   val userViewModel : UserViewModel by viewModels()
    @RequiresApi(Build.VERSION_CODES.S)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DatabaseTestingWithHiltTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    app()
                }
            }
        }
    }


}

@RequiresApi(Build.VERSION_CODES.S)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun app() {

val context = LocalContext.current
    val navController = rememberNavController()

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

            bottomBar(navController)

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
            NavigateToScreen(navController,innerPadding)
        }

    }
}

@Composable
fun bottomBar(navController: NavController) {
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
            IconButton(onClick = { navController.navigate("Search") }) {
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




