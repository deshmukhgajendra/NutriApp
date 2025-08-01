package com.example.databasetestingwithhilt.UiScreens.NavDrawer

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.databasetestingwithhilt.UiScreens.theme.DatabaseTestingWithHiltTheme
import com.example.databasetestingwithhilt.R
import com.example.databasetestingwithhilt.UiScreens.Authentication
import com.example.databasetestingwithhilt.UiScreens.NavDrawer.Screens.ChangePassword
import com.example.databasetestingwithhilt.UiScreens.NavDrawer.Screens.DeleteAccount
import com.example.databasetestingwithhilt.UiScreens.NavDrawer.Screens.EditProfile
import com.example.databasetestingwithhilt.UiScreens.NavDrawer.Screens.MyGoal
import com.example.databasetestingwithhilt.UiScreens.NavDrawer.Screens.NavigationDrawerScreen
import com.example.databasetestingwithhilt.viewmodel.UserViewModel
import com.example.databasetestingwithhilt.ui.theme.OutFitFontFamily
import com.example.databasetestingwithhilt.ui.theme.White
import com.example.databasetestingwithhilt.ui.theme.lightGray
import com.example.databasetestingwithhilt.ui.theme.orange
import com.example.databasetestingwithhilt.ui.theme.purple
import com.example.databasetestingwithhilt.ui.theme.sea
import com.example.databasetestingwithhilt.viewmodel.AuthenticationViewModel
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
fun navigation(navController :NavHostController = rememberNavController()){

    NavHost(navController = navController,
        startDestination = "NavigationDrawerScreen"
    ){
        composable("NavigationDrawerScreen"){
            NavigationDrawerScreen(navController = navController)
        }
        composable(route = "EditProfile"){
            EditProfile(navController = navController)
        }
        composable(route = "MyGoal"){
            MyGoal()
        }
        composable(route = "DeleteAccount"){
            DeleteAccount()
        }
        composable(route = "ChangePassword"){
            ChangePassword()
        }
    }
}