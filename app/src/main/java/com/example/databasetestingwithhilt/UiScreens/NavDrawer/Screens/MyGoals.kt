package com.example.databasetestingwithhilt.UiScreens.NavDrawer.Screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.databasetestingwithhilt.ui.theme.DarkBlue
import com.example.databasetestingwithhilt.ui.theme.OutFitFontFamily
import com.example.databasetestingwithhilt.viewmodel.FirebaseViewmodel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyGoals(firebaseViewmodel: FirebaseViewmodel,navController: NavController){

    val data by firebaseViewmodel.personalData.collectAsState()
    val goals = listOf("Starting weight", "Current Weight", "Goal Weight", " activityLevel")

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "My Goals",
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
    ) { innerPadding->

        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
          //  goals.forEach { items->
                goalItem(
                    onClick = {},
                    buttonTitle = "Starting Weight",
                    data = data,
                    key = "weight"
                )
            goalItem(
                onClick = {},
                buttonTitle = "Current Weight",
                data = data,
                key = "weight"
            )
            goalItem(
                onClick = {},
                buttonTitle = "Goal Weight",
                data = data,
                key = "weight"
            )
            goalItem(
                onClick = {},
                buttonTitle = "Activity Level",
                data = data,
                key = "activityLevel"
            )
        //    }

            Text(
                text = "Current Goal",
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                color = MaterialTheme.colorScheme.secondary,
                fontSize = 16.sp,
                fontFamily = OutFitFontFamily,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(18.dp)
            )

            goalItem(
                onClick = {},
                buttonTitle = "Current Goal",
                data = data,
                key = "goal"
            )
        }
    }
}

@Composable
fun goalItem(onClick: ()-> Unit,
             buttonTitle:String,
             data:Map<String,Any?>,
             key:String){
    Column (modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 8.dp)
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onClick() }
            , verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = buttonTitle,
                style = TextStyle(
                    fontFamily = OutFitFontFamily,
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp
                ),
                modifier = Modifier.padding(start = 20.dp, end = 20.dp)
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = "${data.getValue(key)}",
                style = TextStyle(
                    fontFamily = OutFitFontFamily,
                    fontWeight = FontWeight.Medium,
                    color = DarkBlue,
                    fontSize = 16.sp
                ),
                modifier = Modifier.padding(start = 20.dp, end = 20.dp)
            )

        }

        HorizontalDivider(
            thickness = 1.dp,
            modifier = Modifier
                .padding(top = 12.dp)
            )
    }
}