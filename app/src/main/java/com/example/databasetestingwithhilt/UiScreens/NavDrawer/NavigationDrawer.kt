package com.example.databasetestingwithhilt.UiScreens.NavDrawer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.databasetestingwithhilt.UiScreens.NavDrawer.Screens.Appearances
import com.example.databasetestingwithhilt.UiScreens.NavDrawer.Screens.ChangePassword
import com.example.databasetestingwithhilt.UiScreens.NavDrawer.Screens.DeleteAccount
import com.example.databasetestingwithhilt.UiScreens.NavDrawer.Screens.EditProfile
import com.example.databasetestingwithhilt.UiScreens.NavDrawer.Screens.MyAppsAndDevice
import com.example.databasetestingwithhilt.UiScreens.NavDrawer.Screens.MyGoals
import com.example.databasetestingwithhilt.UiScreens.NavDrawer.Screens.NavigationDrawerScreen
import com.example.databasetestingwithhilt.UiScreens.NavDrawer.Screens.Reminders
import com.example.databasetestingwithhilt.UiScreens.NavDrawer.Screens.Steps
import com.example.databasetestingwithhilt.UiScreens.theme.DatabaseTestingWithHiltTheme
import com.example.databasetestingwithhilt.viewmodel.FirebaseViewmodel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NavigationDrawer : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        enableEdgeToEdge()
        setContent {
            DatabaseTestingWithHiltTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->


                    navigation()
                }
            }
        }
    }
}

//@Composable
//@OptIn(ExperimentalMaterial3Api::class)
//fun navApp(){
//    Scaffold (
//        topBar = {
//            TopAppBar(
//                title = {
//
//                },
//                navigationIcon = {
//
//                }
//            )
//        }
//    ){innerpadding ->
//
//    }
//}

@Composable
fun navigation(navController :NavHostController = rememberNavController(),
               firebaseViewmodel: FirebaseViewmodel= hiltViewModel()
){

    NavHost(navController = navController,
        startDestination = "NavigationDrawerScreen"
    ){
        composable("NavigationDrawerScreen"){
            NavigationDrawerScreen(navController = navController)
        }
        composable(route = "EditProfile"){
            EditProfile(navController = navController,firebaseViewmodel)
        }
        composable(route = "MyGoals"){
            MyGoals(firebaseViewmodel,navController)
        }
        composable(route = "DeleteAccount"){
            DeleteAccount(navController)
        }
        composable(route = "ChangePassword"){
            ChangePassword()
        }
        composable(route = "Appearances" ){
            Appearances()
        }
        composable(route = "Reminders"){
            Reminders()
        }
        composable(route = "Steps"){
            Steps()
        }
        composable(route = "MyAppsAndDevices"){
            MyAppsAndDevice()
        }
    }
}