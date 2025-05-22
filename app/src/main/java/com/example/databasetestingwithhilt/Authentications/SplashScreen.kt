package com.example.databasetestingwithhilt.Authentications

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.databasetestingwithhilt.Authentications.ui.theme.DatabaseTestingWithHiltTheme
import com.example.databasetestingwithhilt.MainActivity
import com.example.databasetestingwithhilt.R
import com.example.databasetestingwithhilt.ui.theme.White
import com.example.databasetestingwithhilt.ui.theme.purple
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
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

    var progress by remember { mutableStateOf(false) }


        LaunchedEffect(Unit) {

            progress = true
            delay(3000)

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


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(R.drawable.splashscreen),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )

        if(progress) {
            LinearProgressIndicator(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(30.dp, bottom = 40.dp)
                    .align(Alignment.BottomCenter),
                color = White,
                trackColor = purple
            )
        }

    }
}


