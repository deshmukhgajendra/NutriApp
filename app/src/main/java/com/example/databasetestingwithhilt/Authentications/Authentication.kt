package com.example.databasetestingwithhilt.Authentications

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.databasetestingwithhilt.PersonalInformation.PersonalInformation
import com.example.databasetestingwithhilt.UserViewModel
import com.example.databasetestingwithhilt.ui.theme.DatabaseTestingWithHiltTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Authentication : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DatabaseTestingWithHiltTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val navController = rememberNavController()
                    NavToScreen(navController)
                }
            }
        }
    }
}

@Composable
fun NavToScreen(navController: NavHostController){

    NavHost(navController = navController,
        startDestination = "IntroScreen"
    ) {
        composable("IntroScreen"){
            IntroScreen(navController)
        }
        composable("SignIn"){
            SignInScreen(navController)
        }
        composable("SignUp"){
            SignUpScreen(navController)
        }
    }
}

