package com.example.databasetestingwithhilt.MenuScreen

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.databasetestingwithhilt.Authentications.Authentication
import com.example.databasetestingwithhilt.MenuScreen.ui.theme.DatabaseTestingWithHiltTheme
import com.example.databasetestingwithhilt.R
import com.example.databasetestingwithhilt.UserViewModel
import com.example.databasetestingwithhilt.ui.theme.gray
import com.example.databasetestingwithhilt.ui.theme.lightGray
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NavigationDrawer : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DatabaseTestingWithHiltTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    NavigationDrawerScreen()

                }
            }
        }
    }
}

@Composable
fun NavigationDrawerScreen(viewModel: UserViewModel = hiltViewModel()) {
    val userName by viewModel.userName.observeAsState()
    val userEmail by viewModel.userEmail.observeAsState()
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.getUserDetails()
    }

    val scrollState = rememberScrollState()
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .clip(RoundedCornerShape(bottomStart = 50.dp, bottomEnd = 50.dp))
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color(0xFF00C6FF),
                            Color(0xFF0072FF)
                        )
                    )
                )
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(15.dp, top = 50.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = "Hello",
                        fontSize = 20.sp,
                        textAlign = TextAlign.Start
                    )
                    Text(
                        text = "$userName",
                        fontSize = 30.sp,
                        fontStyle = FontStyle.Italic,
                        textAlign = TextAlign.Start
                    )
                }

                Image(
                    painter = painterResource(R.drawable.icons_person),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(end = 30.dp)
                        .size(90.dp)
                        .clip(CircleShape)
                        .border(2.dp, Color.White, CircleShape)
                )
            }
            Column(
                modifier = Modifier
                    .padding(top = 100.dp)
                    .verticalScroll(scrollState)
            ) {
                Divider(
                    thickness = 5.dp,
                    modifier = Modifier.padding(top = 4.dp, bottom = 4.dp)
                )
                Text(
                    text = "Account",
                    fontSize = 17.sp,
                    color = lightGray,
                    modifier = Modifier.padding(bottom = 11.dp, start = 10.dp, top = 11.dp)
                )
                listItem("Edit Profile", {})
                listItem("My Goals", {})
                listItem("My apps & Devices", {})
                listItem("Delete Account", {})
                listItem("Change Password", {})
                listItem("Log Out") {
                    Log.d("NavigationDrawerScreen", "Log Out lambda executed")
                    viewModel.logout()
                    val i = Intent(context,Authentication::class.java)
                    context.startActivity(i)
                    (context as? Activity)?.finish()
                }
                Divider(
                    thickness = 5.dp,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
                Text(
                    text = "Settings",
                    fontSize = 17.sp,
                    color = lightGray,
                    modifier = Modifier.padding(bottom = 11.dp, start = 10.dp, top = 11.dp)
                )
                listItem("Appearances", {})
                listItem("Diary Settings", {})
                listItem("Reminders", {})
                listItem("Weekly Nutrition Settings", {})
                listItem("Steps", {})
                listItem("Push Notification", {})
                Divider(
                    thickness = 5.dp,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
                Text(
                    text = "Settings",
                    fontSize = 17.sp,
                    color = lightGray,
                    modifier = Modifier.padding(bottom = 11.dp, start = 10.dp, top = 11.dp)
                )
                listItem("About Us", {})
                listItem("Contact Support", {})
                listItem("FAQs/Feedback", {})
                listItem("Join Beta Program", {})
                listItem("Troubleshooting", {})
            }
        }
    }
}

@Composable
fun listItem(item: String, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Button(
            onClick = {
                Log.d("listItem", "Button clicked for item: $item")
                onClick()
            },
            modifier = Modifier
                .fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent,
                contentColor = MaterialTheme.colorScheme.onBackground
            ),
            contentPadding = ButtonDefaults.ContentPadding
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start
            ) {
                Text(
                    text = item,
                    fontSize = 16.sp,
                    color = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        }

        Divider(
            thickness = 1.dp,
            color = MaterialTheme.colorScheme.surfaceVariant,
            modifier = Modifier.fillMaxWidth()
        )
    }
}
