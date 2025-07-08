package com.example.databasetestingwithhilt.UiScreens.SleepScreen

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Build
import android.os.Bundle
import android.widget.TextClock
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.databasetestingwithhilt.R
import com.example.databasetestingwithhilt.UiScreens.SleepScreen.ui.theme.DatabaseTestingWithHiltTheme
import com.example.databasetestingwithhilt.viewmodel.UserViewModel
import com.example.databasetestingwithhilt.ui.theme.OutFitFontFamily
import com.example.databasetestingwithhilt.ui.theme.lightBlue
import com.example.databasetestingwithhilt.ui.theme.yellow
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ScreenSaver : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DatabaseTestingWithHiltTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                   SleepScreenSaver()
                }
            }
        }
    }
}

@SuppressLint("SuspiciousIndentation")
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SleepScreenSaver(viewModel: UserViewModel = hiltViewModel() ) {

    val context = LocalContext.current
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(R.drawable.sleepbackground),
            contentDescription = null,
            modifier = Modifier.matchParentSize(),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 450.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.Center
            ) {
                AndroidView(
                    factory = { context ->
                        TextClock(context).apply {
                            // set hour format
                            format12Hour = "hh:mm"
                            // set time zone
                            timeZone = timeZone
                            // set text size
                            textSize = 70f
                            // set text color
                            setTextColor(ContextCompat.getColor(context, R.color.white))
                        }
                    },
                  //  modifier = Modifier.padding(5.dp),
                )
                Text(
                    text = "PM",
                    fontSize = 30.sp,
                    color = Color.White,
                    modifier = Modifier.padding(start = 4.dp),
                    textAlign = TextAlign.Center,
                    fontFamily = OutFitFontFamily,
                    fontWeight = FontWeight.Normal
                )
            }
            Text(
                text = "Sat, Apr 19",
                fontSize = 20.sp,
                color = Color.White.copy(alpha = 0.7f),
                textAlign = TextAlign.Center,
                fontFamily = OutFitFontFamily,
                fontWeight = FontWeight.Normal,
                modifier = Modifier.padding(top = 2.dp)
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(bottom = 100.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Card(
                onClick = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
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
                        fontFamily = OutFitFontFamily,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.weight(1f)
                    )
                    Text(
                        text = "12:15 PM",
                        textAlign = TextAlign.End,
                        color = Color.White,
                        fontFamily = OutFitFontFamily,
                        fontWeight = FontWeight.Normal
                    )
                }
            }

            HorizontalDivider(thickness = 2.dp, modifier = Modifier.padding(horizontal = 30.dp))

            Card(
                onClick = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
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
                        fontFamily = OutFitFontFamily,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.weight(1f)
                    )
                    Text(
                        text = "Off",
                        textAlign = TextAlign.End,
                        color = Color.White,
                        fontFamily = OutFitFontFamily,
                        fontWeight = FontWeight.Normal
                    )
                }
            }

            Spacer(modifier = Modifier.height(40.dp))

            Button(
                onClick = {
               val waketime = getCurrentTime()
                    viewModel.SaveWakeTime(waketime)

                    (context as? Activity)?.finish()

                },
                modifier = Modifier
                    .padding(horizontal = 30.dp)
                    .height(50.dp)
                    .fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White
                )
            ) {
                Text(
                    text = "Wake Up",
                    fontSize = 20.sp,
                    fontFamily = OutFitFontFamily,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}