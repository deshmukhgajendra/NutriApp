package com.example.databasetestingwithhilt.Authentications

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.databasetestingwithhilt.Authentications.ui.theme.DatabaseTestingWithHiltTheme
import com.example.databasetestingwithhilt.MainActivity
import com.example.databasetestingwithhilt.R
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SplashScreen : ComponentActivity() {

    @Inject
    lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DatabaseTestingWithHiltTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Splashscreen(firebaseAuth)
                }
            }
        }
    }
}
@Composable
fun Splashscreen(firebaseAuth: FirebaseAuth) {
    val context = LocalContext.current

    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.splashscreen))
    val progress by animateLottieCompositionAsState(
        composition = composition,
        iterations = 1 // Play animation once
    )

    // Check if animation is complete
    if (progress == 1f) {
        LaunchedEffect(Unit) {
            val currentUser = firebaseAuth.currentUser
            if (currentUser != null) {
                // Navigate to MainActivity if user is logged in
                context.startActivity(Intent(context, MainActivity::class.java))
            } else {
                // Navigate to LoginActivity if no user is logged in
                context.startActivity(Intent(context, Authentication::class.java))
            }
            (context as? Activity)?.finish() // Close SplashActivity
        }
    }

    // Lottie Animation Display
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        LottieAnimation(
            composition = composition,
            progress = progress
        )
    }
}


