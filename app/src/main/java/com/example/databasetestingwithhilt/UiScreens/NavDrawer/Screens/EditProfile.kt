package com.example.databasetestingwithhilt.UiScreens.NavDrawer.Screens

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.databasetestingwithhilt.ui.theme.OutFitFontFamily
import com.example.databasetestingwithhilt.ui.theme.White
import com.example.databasetestingwithhilt.ui.theme.purple


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditProfile(navController: NavController){

    Scaffold (
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Edit Profile",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        color = MaterialTheme.colorScheme.secondary,
                        fontSize = 22.sp,
                        fontFamily = OutFitFontFamily,
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                  IconButton(
                     onClick = {navController.navigate("NavigationDrawerScreen")}
                  ) {
                      Icon(
                          imageVector = Icons.Filled.ArrowBack,
                          contentDescription = null
                      )
                  }
                }
            )
        }
    ){innerPadding ->

    }
}

@Composable
fun profileScreen(){
    Column (modifier = Modifier.fillMaxSize()){
       // profileItem()
    }
}
@Composable
fun profileItem(onClick: () -> Unit,
                buttonTitle:String,
                @DrawableRes iconResId:Int
){
    Column(modifier = Modifier
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
                    .background(purple),
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