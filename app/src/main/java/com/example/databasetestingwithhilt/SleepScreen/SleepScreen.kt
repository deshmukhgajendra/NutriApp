package com.example.databasetestingwithhilt.SleepScreen

import android.content.Intent
import android.os.Build
import android.view.View
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.databasetestingwithhilt.R
import com.example.databasetestingwithhilt.UserViewModel
import com.example.databasetestingwithhilt.ui.theme.DarkBlue
import com.example.databasetestingwithhilt.ui.theme.gray
import com.example.databasetestingwithhilt.ui.theme.lightBlue
import com.example.databasetestingwithhilt.ui.theme.yellow


@Composable
fun SleepScreen(viewModel: UserViewModel = hiltViewModel()) {

    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Black),
        horizontalAlignment = Alignment.CenterHorizontally // Center content horizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp), // Adjust height to ensure vertical centering
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(
                progress = 0.7f,
                color = DarkBlue,
                strokeWidth = 20.dp,
                trackColor = gray,
                modifier = Modifier
                    .size(250.dp)
                    .padding(20.dp)
            )
            Text(text = "Track my sleep")
        }

        Spacer(modifier = Modifier.height(50.dp))

        Card(
            onClick = {},
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color.Transparent),
            elevation = CardDefaults.cardElevation(0.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = {},
                    modifier = Modifier.padding(end = 4.dp)
                ) {
                    Icon(
                        painter = painterResource(R.drawable.sleep),
                        contentDescription = null,
                        tint = lightBlue
                    )
                }

                Text(
                    text = "Bedtime",
                    fontSize = 20.sp,
                    textAlign = TextAlign.Start,
                    color = Color.White,
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = "12:15 PM",
                    textAlign = TextAlign.End,
                    color = Color.White
                )
            }
        }

        HorizontalDivider(thickness = 2.dp, modifier = Modifier.padding(horizontal = 30.dp))

        Card(
            onClick = {},
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color.Transparent),
            elevation = CardDefaults.cardElevation(0.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = {},
                    modifier = Modifier.padding(end = 4.dp)
                ) {
                    Icon(
                        painter = painterResource(R.drawable.alarm),
                        contentDescription = null,
                        tint = yellow
                    )
                }

                Text(
                    text = "Alarm",
                    fontSize = 20.sp,
                    textAlign = TextAlign.Start,
                    color = Color.White,
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = "Off",
                    textAlign = TextAlign.End,
                    color = Color.White
                )
            }
        }

        Spacer(modifier = Modifier.height(50.dp))

        Button(
            onClick = {
                val sleepTime = viewModel.setSleepTime() // Return the time
                viewModel.setSleepTime()
               // navController.navigate("ScreenSaver")

                // val i = Intent(context,ScreenSaver::class.java).apply {
                  //  putExtra("SLEEP_TIME", sleepTime)
                //}
               // context.startActivity(i)
            },
            modifier = Modifier
                .padding(horizontal = 30.dp)
                .fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.White
            )
        ) {
            Text(text = "Sleep Now", fontSize = 20.sp)
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SleepScreenNavigation(navController: NavHostController, viewModel: UserViewModel){

    val navController = rememberNavController()

    NavHost(navController = navController,
        startDestination = "SleepScreen"
    ) {
        composable("SleepScreen"){
          //  SleepScreen(navController)
        }
        composable("ScreenSaver"){
            SleepScreenSaver(navController,viewModel)
        }
    }
}

