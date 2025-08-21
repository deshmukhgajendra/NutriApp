package com.example.databasetestingwithhilt.UiScreens.NavDrawer.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.databasetestingwithhilt.R
import com.example.databasetestingwithhilt.ui.theme.OutFitFontFamily
import com.google.android.gms.common.SignInButton.ButtonSize

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Appearances(){

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Appearance",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        color = MaterialTheme.colorScheme.secondary,
                        fontSize = 22.sp,
                        fontFamily = OutFitFontFamily,
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = {}
                    ) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = null
                        )
                    }
                }
            )
        }
    ) { innerPadding ->

        val button = listOf(R.drawable.adaptive,R.drawable.light_theme,R.drawable.darktheme)

        Column (
            modifier = Modifier
                .fillMaxWidth()
                .padding(innerPadding)
        ){
            button.forEach { image->
                ThemeButtons(image,{})
            }
        }
    }
}

@Composable
fun ThemeButtons(
    ButtonImage :Int,
    onChecked : (Boolean)-> Unit
){
    Card (
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .height(120.dp)
    ){
        Box(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Row {
                Checkbox(
                    checked = false,
                    onCheckedChange = onChecked
                )
                Image(
                    painter = painterResource(ButtonImage),
                    contentDescription = "buttonImage",
                    contentScale = ContentScale.Crop
                )
            }

        }
    }
}