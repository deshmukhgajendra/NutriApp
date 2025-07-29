package com.example.databasetestingwithhilt.UiScreens.SleepScreen


import android.content.Intent
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.databasetestingwithhilt.R
import com.example.databasetestingwithhilt.viewmodel.UserViewModel
import com.example.databasetestingwithhilt.ui.theme.DarkBlue
import com.example.databasetestingwithhilt.ui.theme.OutFitFontFamily
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
        horizontalAlignment = Alignment.CenterHorizontally 
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp),
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
            Text(
                text = "Track my sleep",
                fontFamily = OutFitFontFamily,
                fontWeight = FontWeight.Bold
            )
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
                    fontFamily = OutFitFontFamily,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Start,
                    color = Color.White,
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = "12:15 PM",
                    textAlign = TextAlign.End,
                    color = Color.White,
                    fontFamily = OutFitFontFamily,
                    fontWeight = FontWeight.Bold
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
                    fontFamily = OutFitFontFamily,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Start,
                    color = Color.White,
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = "Off",
                    textAlign = TextAlign.End,
                    color = Color.White,
                    fontFamily = OutFitFontFamily,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        Spacer(modifier = Modifier.height(50.dp))

        Button(
            onClick = {

                val currentdate = getCurrentDate()
                val sleeptime = getCurrentTime()

                viewModel.SaveSleepTime(currentdate,sleeptime)

                val intent = Intent(context, ScreenSaver::class.java).apply {
                    putExtra("currentdate",currentdate)
                    putExtra("sleeptime",sleeptime)
                }
                context.startActivity(intent)
            },
            modifier = Modifier
                .height(60.dp)
                .padding(horizontal = 30.dp)
                .fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.White
            )
        ) {
            Text(
                text = "Sleep Now",
                fontSize = 20.sp,
                fontFamily = OutFitFontFamily,
                fontWeight = FontWeight.Bold)
        }
    }
}

fun getCurrentTime():String{
    val timeFormate = java.text.SimpleDateFormat("hh:mm", java.util.Locale.getDefault())
    return timeFormate.format(java.util.Date())
}

fun getCurrentDate(): String{
    val dateFormate = java.text.SimpleDateFormat("dd:mm:yyyy", java.util.Locale.getDefault())
    return dateFormate.format(java.util.Date())
}