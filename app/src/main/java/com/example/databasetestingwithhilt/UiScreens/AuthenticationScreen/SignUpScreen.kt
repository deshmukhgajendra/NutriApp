package com.example.databasetestingwithhilt.UiScreens.AuthenticationScreen

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.databasetestingwithhilt.R
import com.example.databasetestingwithhilt.UiScreens.PersonalInformations.PersonalInformation
import com.example.databasetestingwithhilt.ui.theme.Black
import com.example.databasetestingwithhilt.ui.theme.OutFitFontFamily
import com.example.databasetestingwithhilt.ui.theme.White
import com.example.databasetestingwithhilt.viewmodel.AuthenticationViewModel



@SuppressLint("SuspiciousIndentation")
@Composable
fun SignUpScreen(navController: NavController,
                authViewModel : AuthenticationViewModel = hiltViewModel()
){

    val context = LocalContext.current
    val state by authViewModel.authState.collectAsState()


//    // Google Sign-In launcher
//    val launcher = rememberLauncherForActivityResult(
//        contract = ActivityResultContracts.StartActivityForResult()
//    ) { result ->
//        val intent = result.data
//        authViewModel.googleSignIn(intent)
//    }

    LaunchedEffect(state) {
        if(state != null){
            val intent = Intent(context, PersonalInformation::class.java)
            context.startActivity(intent)
            (context as Activity).finish()
        }
    }

        Box(
            modifier = Modifier
                .fillMaxSize()
            
        ) {
            // Back Button (Top-left)

            Image(
                painter = painterResource(R.drawable.loginbackground),
                contentScale = ContentScale.Crop,
                contentDescription = null
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                contentAlignment = Alignment.TopStart
            ) {
                IconButton(onClick = { navController.navigate("IntroScreen") }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        tint = Color.White,
                        modifier = Modifier
                            .size(32.dp)
                            .background(Color.Black, shape = CircleShape)
                    )
                }
            }

            // Bottom Card
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.75f)  // Covers 1/4th of the screen height
                    .align(Alignment.BottomCenter)
                    .clip(
                        RoundedCornerShape(
                            topStart = 40.dp,
                            topEnd = 40.dp
                        )
                    ) // Rounded top corners
                    .background(Color.Black)
                    .padding(16.dp)
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Title
                    Text(
                        text = "Get Started",
                        fontSize = 24.sp,
                        color = White,
                        fontFamily = OutFitFontFamily,
                        fontWeight = FontWeight.Bold

                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    // Input Fields
                  //  val fullName = remember { mutableStateOf("") }
                    val email = remember { mutableStateOf("") }
                    val password = remember { mutableStateOf("") }
                    val isChecked = remember { mutableStateOf(false) }

                    Column(
                        modifier = Modifier.fillMaxWidth(0.9f)
                    ) {
//                        OutlinedTextField(
//                            value = fullName.value,
//                            onValueChange = { fullName.value = it },
//                            label = {
//                                Text(
//                                    "Enter Full Name",
//                                    fontFamily = OutFitFontFamily,
//                                    fontWeight = FontWeight.Normal) },
//                            singleLine = true,
//                            modifier = Modifier.fillMaxWidth(),
//                            shape = RoundedCornerShape(18.dp),
//                            colors = TextFieldDefaults.colors(
//                                focusedTextColor = Color.White,
//                                unfocusedTextColor = Color.White,
//
//                                focusedContainerColor = Color.Transparent,
//                                unfocusedContainerColor = Color.Transparent,
//
//                                focusedLabelColor = Color.White,
//                                unfocusedLabelColor = Color.Gray,
//
//                                focusedIndicatorColor = Color.White,
//                                unfocusedIndicatorColor = Color.Gray,
//
//                                cursorColor = Color.White
//                            )
//                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        OutlinedTextField(
                            value = email.value,
                            onValueChange = { email.value = it },
                            label = {
                                Text(
                                    "Enter Email",
                                    fontFamily = OutFitFontFamily,
                                    fontWeight = FontWeight.Normal) },
                            singleLine = true,
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(18.dp),
                            colors = TextFieldDefaults.colors(
                                focusedTextColor = Color.White,
                                unfocusedTextColor = Color.White,

                                focusedContainerColor = Color.Transparent,
                                unfocusedContainerColor = Color.Transparent,

                                focusedLabelColor = Color.White,
                                unfocusedLabelColor = Color.Gray,

                                focusedIndicatorColor = Color.White,
                                unfocusedIndicatorColor = Color.Gray,

                                cursorColor = Color.White
                            )
                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        OutlinedTextField(
                            value = password.value,
                            onValueChange = { password.value = it },
                            label = {
                                Text(
                                    "Enter Password",
                                    fontFamily = OutFitFontFamily,
                                    fontWeight = FontWeight.Normal) },
                            singleLine = true,
                            visualTransformation = PasswordVisualTransformation(),
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(18.dp),
                            colors = TextFieldDefaults.colors(
                                focusedTextColor = Color.White,
                                unfocusedTextColor = Color.White,

                                focusedContainerColor = Color.Transparent,
                                unfocusedContainerColor = Color.Transparent,

                                focusedLabelColor = Color.White,
                                unfocusedLabelColor = Color.Gray,

                                focusedIndicatorColor = Color.White,
                                unfocusedIndicatorColor = Color.Gray,

                                cursorColor = Color.White
                            )
                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        // Terms and Conditions Checkbox
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Checkbox(
                                checked = isChecked.value,
                                onCheckedChange = { isChecked.value = it }
                            )
                            ClickableText(
                                text = AnnotatedString("I agree to the processing of Personal data"),
                                onClick = { /* Handle link click */ },
                                style = TextStyle(color = White, fontSize = 14.sp)
                            )
                        }

                        Spacer(modifier = Modifier.height(20.dp))

                        // Sign-Up Button
                        Button(
                            onClick = {
                                authViewModel.register(email.value,password.value,context)
                                Toast.makeText(context, "Account Created Successfully", Toast.LENGTH_SHORT).show()

                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp)
                                ,
                               colors = ButtonDefaults.buttonColors(
                                   containerColor =White,
                                   contentColor = Black
                               )
                        ) {
                            Text(
                                text = "Sign up",
                                color = Black,
                                fontSize = 18.sp,
                                fontFamily = OutFitFontFamily,
                                fontWeight = FontWeight.Normal)
                        }

                        Spacer(modifier = Modifier.height(20.dp))

                        // Sign-Up with Social Media
                        Text(
                            "Sign-up with",
                            fontSize = 16.sp,
                            color = Color.Gray,
                            fontFamily = OutFitFontFamily,
                            fontWeight = FontWeight.Normal)

                        Spacer(modifier = Modifier.height(10.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            IconButton(onClick = { /* Handle Phone Login */ }) {
                                Icon(
                                    painter = painterResource(id = R.drawable.baseline_call_24),
                                    contentDescription = "Phone",
                                    tint = Color.Unspecified
                                )
                            }
                            IconButton(onClick = {
//                                val signInIntent = authViewModel.getGoogleSignInIntent()
//                                launcher.launch(signInIntent)
                            }) {
                                Icon(
                                    painter = painterResource(id = R.drawable.google),
                                    contentDescription = "Google",
                                    tint = Color.Unspecified
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(20.dp))

                        // Already have an account?
                        Row {
                            Text("Already have an account?", fontSize = 14.sp, color = Color.Gray)
                            Spacer(modifier = Modifier.width(5.dp))
                            ClickableText(
                                text = AnnotatedString("Sign in"),
                                onClick = { navController.navigate("SignIn") },
                                style = TextStyle(
                                    color = White,
                                    fontSize = 14.sp,
                                    fontFamily = OutFitFontFamily,
                                    fontWeight = FontWeight.Bold
                                )
                            )
                        }
                    }
                }
            }
        }
    }
