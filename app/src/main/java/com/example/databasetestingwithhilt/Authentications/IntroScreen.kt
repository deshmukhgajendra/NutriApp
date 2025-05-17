package com.example.databasetestingwithhilt.Authentications

import android.app.Activity
import android.content.Intent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.databasetestingwithhilt.MainActivity
import com.example.databasetestingwithhilt.R


@Composable
fun IntroScreen(navController: NavController){

    val context = LocalContext.current
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)

    ) {
        IconButton(
            onClick = {
                val i=Intent(context,MainActivity::class.java)
                context.startActivity(i)
                (context as Activity).finish()
            },
            modifier = Modifier.align(Alignment.TopEnd)
                .padding(30.dp)
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back",
                tint = Color.White,
                modifier = Modifier
                    .size(32.dp)
                    .background(Color.Black, shape = CircleShape)
            )
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 100.dp),
            contentAlignment = Alignment.TopCenter
        ) {

            Image(
                painter = painterResource(R.drawable.restify),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
            Text(
                text = "Welcome to !",
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp)
                .height(120.dp) // Increased height
                .align(Alignment.BottomCenter),
            contentAlignment = Alignment.Center
        ) {
            Row(
                modifier = Modifier
                    .width(350.dp) // Fixed width instead of fillMaxWidth
                    .height(80.dp), // Increased button height
                horizontalArrangement = Arrangement.spacedBy(16.dp), // Added gap between buttons
                verticalAlignment = Alignment.CenterVertically
            ) {
                FilledTonalButton(
                    onClick = { navController.navigate("SignIn") },
                    modifier = Modifier
                        .weight(1f)
                        .height(50.dp), // Increased button height
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White,
                        contentColor = Color.Black
                    ),
                    shape = RoundedCornerShape(36.dp) // Rounded corners
                ) {
                    Text(
                        text = "Login",
                        color = Color.Black,
                        fontSize = 16.sp // Slightly larger text
                    )
                }

                OutlinedButton(
                    onClick = { navController.navigate("SignUp") },
                    modifier = Modifier
                        .weight(1f)
                        .height(50.dp), // Increased button height
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent,
                        contentColor = Color.White
                    ),
                    border = BorderStroke(1.dp, Color.White), // White border
                    shape = RoundedCornerShape(36.dp) // Rounded corners
                ) {
                    Text(
                        text = "Sign Up",
                        color = Color.White,
                        fontSize = 16.sp // Slightly larger text
                    )
                }
            }
        }
    }
}