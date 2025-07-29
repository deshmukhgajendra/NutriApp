package com.example.databasetestingwithhilt.UiScreens

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
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
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
import com.example.databasetestingwithhilt.UiScreens.theme.DatabaseTestingWithHiltTheme
import com.example.databasetestingwithhilt.R
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

                    NavigationDrawerScreen()

                }
            }
        }
    }
}

@Composable
fun NavigationDrawerScreen(viewModel: UserViewModel = hiltViewModel(),
                           authViewModel :AuthenticationViewModel= hiltViewModel(),
                           firebaseViewmodel: FirebaseViewmodel = hiltViewModel()
) {
    val userName by firebaseViewmodel.userName.observeAsState()
    val userEmail by firebaseViewmodel.userEmail.observeAsState()
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        firebaseViewmodel.getUserDetails()
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
        ){
            Image(
                painter = painterResource(R.drawable.menubackground),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(15.dp, top = 50.dp)
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                //horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Image(
                    painter = painterResource(R.drawable.icons_person),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(start = 30.dp)
                        .size(80.dp)
                        .clip(CircleShape)
                        .border(2.dp, Color.White, CircleShape)
                )
                Spacer(modifier = Modifier.width(22.dp))

                Column {
                    Text(
                        text = "Hello",
                        fontSize = 25.sp,
                        textAlign = TextAlign.Start,
                        fontFamily = OutFitFontFamily,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.secondary
                    )
                    Text(
                        text = "$userName",
                        fontSize = 30.sp,
                        fontFamily = OutFitFontFamily,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Start,
                        color = MaterialTheme.colorScheme.secondary

                    )
                }
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
                    fontFamily = OutFitFontFamily,
                    fontWeight = FontWeight.Normal,
                    modifier = Modifier.padding(bottom = 11.dp, start = 10.dp, top = 11.dp)
                )
                listItem("Edit Profile",R.drawable.baseline_assignment_24, purple, {})
                listItem("My Goals", R.drawable.flag, purple, {})
                listItem("My apps & Devices",R.drawable.baseline_smartphone_24, purple, {})
                listItem("Delete Account",R.drawable.baseline_no_accounts_24, purple, {})
                listItem("Change Password",R.drawable.baseline_password_24,purple, {})
                listItem("Log Out",R.drawable.baseline_logout_24, purple
                ) {
                    Log.d("NavigationDrawerScreen", "Log Out lambda executed")
                    authViewModel.logout()
                    val i = Intent(context, Authentication::class.java).apply {
                        flags =Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    }
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
                listItem("Appearances",R.drawable.baseline_preview_24, sea, {})
                listItem("Diary Settings",R.drawable.baseline_assignment_add_24, sea, {})
                listItem("Reminders",R.drawable.alarm, sea, {})
                listItem("Steps",R.drawable.shoe_prints_svgrepo_com, sea, {})
                listItem("Push Notification",R.drawable.alarm, sea, {})
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
                listItem("About Us",R.drawable.baseline_tag_faces_24, orange, {})
                listItem("Contact Support",R.drawable.baseline_call_24, orange, {})
                listItem("FAQs/Feedback",R.drawable.baseline_feedback_24, orange, {})
                listItem("Join Beta Program",R.drawable.round_question_mark_24, orange, {})
                listItem("Troubleshooting",R.drawable.baseline_security_24, orange, {})
            }
        }
    }
}

@Composable
fun listItem(buttonTitle: String,
             @DrawableRes iconResId: Int,
             color: Color,
             onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
        ,verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(35.dp)
                .clip(RoundedCornerShape(20))
                .background(color),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = iconResId),
                contentDescription = "Icon for $buttonTitle",
                tint = White,
                modifier = Modifier.size(25.dp)
            )
        }
        Spacer(modifier = Modifier.width(18.dp))
        Text(
            text = buttonTitle,
            style = TextStyle(
                fontFamily = OutFitFontFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp
            ),
            modifier = Modifier.weight(1f)
        )
    }
        Divider(
            thickness = 1.dp,
            color = MaterialTheme.colorScheme.surfaceVariant,
            modifier = Modifier.fillMaxWidth()
                .padding(top= 12.dp)
        )
    }
}
